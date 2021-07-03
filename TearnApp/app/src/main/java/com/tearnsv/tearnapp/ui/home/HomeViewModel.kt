package com.tearnsv.tearnapp.ui.home

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.data.Recommendations
import com.tearnsv.tearnapp.repository.TearnRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: TearnRepository) : ViewModel() {

    var recommendations = MutableLiveData<Recommendations>()
    var loading = MutableLiveData(View.GONE)
    //val ID = MutableLiveData("60b0a724b4099b13d593ce39")
    var ID = ""
    val pattern = MutableLiveData("")

    init {
        ID = TearnApplication.prefs.getId().toString()
        loading.value = View.VISIBLE
        pattern.value = randomBooks()

        viewModelScope.launch {
            try {
                recommendations.value = repository.findAllRecommendations(ID)
            }catch (e: Exception){
                Log.e("error", e.toString())
            } finally {
                loading.value = View.GONE
            }
        }
    }

    private fun randomBooks() =
        listOf("Calculo", "Sociología", "Quimica", "Fisica").random()

    val fetchBookResponse = pattern.distinctUntilChanged().switchMap { pat ->
        liveData(Dispatchers.IO) {
            try {
                emit(repository.getBooks(pat))
            } catch (error: Exception) {
                Log.e("BOOK_ERROR", error.toString())
            }
        }
    }
}