package com.tearnsv.tearnapp.ui.account.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.data.NewTutor
import com.tearnsv.tearnapp.repository.TearnRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AccountViewModel(private val repository: TearnRepository) : ViewModel() {

  val idUser = MutableLiveData("")

  // FIRST FRAGMENT
  var names = MutableLiveData("")
  var lastnames = MutableLiveData("")
  var email = MutableLiveData("")
  var languages = mutableListOf<String>()

  // SECOND FRAGMENT
  var dob = MutableLiveData("")
  var description = MutableLiveData("")

  // AVAILABILITY FRAGMENT
  var subject = MutableLiveData("")
  var course = MutableLiveData("")
  var responseTime = MutableLiveData("")
  val idSubject = MutableLiveData<MutableList<String>>(mutableListOf())
  val idCourse = MutableLiveData<MutableList<String>>(mutableListOf())
  var availabilityList = mutableListOf<String>()

  // TUTOR INFORMATION
  var namesInformation = MutableLiveData("")
  var lastnamesInformation = MutableLiveData("")
  var descriptionInformation = MutableLiveData("")
  var dobInformation = MutableLiveData("")
  var responseInformation = MutableLiveData("")
  var languageInformation = mutableListOf<String>()
  var availabilityInformation = mutableListOf<String>()

  //HANDLE ERROR
  var error = false

  val fetchUserAccountResponse = idUser.distinctUntilChanged().switchMap { id ->
    liveData(Dispatchers.IO) {
      try {
        emit(repository.getOneUserAccount(id))
        error = false

      } catch (error: Exception) {
        Log.e("ERROR_USER", error.toString())
        this@AccountViewModel.error = true
      }
    }
  }
  val fetchFavoriteUsers = idUser.distinctUntilChanged().switchMap { id ->
    liveData(Dispatchers.IO) {
      try {
        emit(repository.getFavoriteUserFromUser(id))
        error = false

      } catch (error: Exception) {
        Log.e("ERROR_USER", error.toString())
        this@AccountViewModel.error = true
      }
    }
  }
  val fetchSubjectsResponse = idUser.distinctUntilChanged().switchMap {
    liveData(Dispatchers.IO) {
      try {
        emit(repository.getAllSubjects())
        error = false
      } catch (error: Exception) {
        Log.e("ERROR_USER", error.toString())
        this@AccountViewModel.error = true
      }
    }
  }

  val fetchTutorInformation = idUser.distinctUntilChanged().switchMap {
    liveData(Dispatchers.IO) {
      try {
        emit(repository.getTutor(it))
        error = false
      } catch (error: Exception) {
        Log.e("ERROR_USER", error.toString())
        this@AccountViewModel.error = true
      }
    }
  }


  fun setIdUser(id: String) {
    if (id.isEmpty()) {
      idUser.value = "60c431241d400c001577da6f"
      return
    }
    idUser.value = id
  }

  fun verifyInputsPrincipalFragment(): Boolean {
    if (names.value.isNullOrEmpty() || lastnames.value.isNullOrEmpty() || email.value.isNullOrEmpty())
      return false
    return true
  }

  fun verifyInputsPrincipalFragmentTwo(): Boolean {
    val MAX_LENGTH = 120
    val dodRegex =
      Regex("""^(?:3[01]|[12][0-9]|0?[1-9])([\-/.])(0?[1-9]|1[0-2])\1\d{4}${'$'}""")
    if (dob.value.isNullOrEmpty() || !dodRegex.containsMatchIn(dob.value!!) || description.value.isNullOrEmpty() || description.value!!.length > MAX_LENGTH)
      return false
    return true
  }

  fun verifyDropdownInputs(): Boolean {
    if (subject.value.isNullOrEmpty() || course.value.isNullOrEmpty() || responseTime.value.isNullOrEmpty())
      return false
    return true
  }

  fun verifyAvailability() = availabilityList.isEmpty()
  fun verifyLanguages() = languages.isEmpty()

  fun convertToTutor() {
    viewModelScope.launch {
      try {
        val newTutor = NewTutor(
          idUser.value!!,
          idCourse.value!!.toList(),
          idSubject.value!!.toList(),
          "${names.value} ${lastnames.value}",
          dob.value!!,
          description.value!!,
          responseTime.value!!,
          availabilityList.map { it.toLowerCase(Locale.ROOT).capitalize(Locale.ROOT) }.toSet()
            .toList(),
          languages.toList(),
        )

        repository.convertToTutor(newTutor)
        error = false
        setIdUser(TearnApplication.prefs.getId()!!)

      } catch (error: Exception) {
        Log.e("INSERT_ERROR", error.toString())
        this@AccountViewModel.error = true
      }
    }
  }

  fun updateTutor(tutor: NewTutor) {
    viewModelScope.launch {
      try {
        repository.convertToTutor(tutor)
        error = false
        setIdUser(TearnApplication.prefs.getId()!!)

      } catch (error: Exception) {
        Log.e("UPDATE_ERROR", error.toString())
        this@AccountViewModel.error = true
      }
    }
  }

  fun areValidUpdated(): Boolean {

    if (namesInformation.value.isNullOrEmpty() ||
      lastnamesInformation.value.isNullOrEmpty() ||
      dobInformation.value.isNullOrEmpty() ||
      responseInformation.value.isNullOrEmpty() ||
      descriptionInformation.value.isNullOrEmpty()
    )
      return false

    return true
  }

  fun cleanDatabase(){
    viewModelScope.launch {
     try {
         repository.deleteDatabase()
     } catch (e: Exception){
       Log.e("error",e.toString())
     }
    }
  }
}