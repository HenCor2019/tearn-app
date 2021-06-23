package com.tearnsv.tearnapp.ui.search.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.tearnsv.tearnapp.repository.TearnRepository
import kotlinx.coroutines.Dispatchers

class SearchViewModel(repository: TearnRepository) : ViewModel() {
    var pattern = MutableLiveData("")

    init {
        pattern.value = ""
    }

    val fetchSearchResponse = pattern.distinctUntilChanged().switchMap { newPattern ->
        liveData(Dispatchers.IO) {
            try {
                emit(repository.getAllSearchResponse(newPattern))
            } catch (error: Exception) {
                Log.e("SEARCHING", error.toString())
            }
        }
    }

    val fetchBooksResponse = pattern.distinctUntilChanged().switchMap { newPattern ->
        liveData(Dispatchers.IO) {
            try {
                val modifiedPattern = if(newPattern == "") "calculo" else newPattern
                emit(repository.getBooks(modifiedPattern))
            } catch (error: Exception) {
                Log.e("BOOK_ERROR", error.toString())
            }
        }
    }

    fun setPattern(newPattern: String) {
        pattern.value = newPattern

    }

}
