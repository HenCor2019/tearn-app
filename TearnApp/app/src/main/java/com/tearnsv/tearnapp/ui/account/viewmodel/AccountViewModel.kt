package com.tearnsv.tearnapp.ui.account.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.tearnsv.tearnapp.data.NewTutor
import com.tearnsv.tearnapp.repository.TearnRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountViewModel(private val repository: TearnRepository) : ViewModel() {

    val idUser = MutableLiveData("")

    // FIRST FRAGMENT
    var names = MutableLiveData("")
    var lastnames = MutableLiveData("")
    var email = MutableLiveData("")
    var availabilityList = mutableListOf(1, 1)
    val languages = mutableListOf("Español", "Ingles")

    // SECOND FRAGMENT
    var dob = MutableLiveData("")
    var description = MutableLiveData("")

    // AVAILABILITY FRAGMENT
    var subject = MutableLiveData("")
    var course = MutableLiveData("")
    var responseTime = MutableLiveData("")
    val idSubject = MutableLiveData<MutableList<String>>(mutableListOf())
    val idCourse = MutableLiveData<MutableList<String>>(mutableListOf())

    val fetchUserAccountResponse = idUser.distinctUntilChanged().switchMap { id ->
        liveData(Dispatchers.IO) {
            try {
                emit(repository.getOneUserAccount(id))

            } catch (error: Exception) {
                Log.e("ERROR_USER", error.toString())
            }
        }
    }
    val fetchFavoriteUsers = idUser.distinctUntilChanged().switchMap { id ->
        liveData(Dispatchers.IO) {
            try {
                emit(repository.getFavoriteUserFromUser(id))

            } catch (error: Exception) {
                Log.e("ERROR_USER", error.toString())
            }
        }
    }
    val fetchSubjectsResponse = idUser.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO) {
            try {
                emit(repository.getAllSubjects())
            } catch (error: Exception) {
                Log.e("ERROR_USER", error.toString())
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

    fun verifyAvailability() = availabilityList.filter { it != 0 }.isEmpty()
    fun verifyLanguages() = languages.filter { it != "" }.isEmpty()

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
                    availabilityList.toList(),
                    languages.toList(),
                )

                repository.convertToTutor(newTutor)

            } catch (error: Exception) {
                Log.e("INSERT_ERROR", error.toString())
            }
        }
    }
}