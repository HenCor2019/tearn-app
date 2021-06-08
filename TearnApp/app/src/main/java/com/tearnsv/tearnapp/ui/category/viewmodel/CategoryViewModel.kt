package com.tearnsv.tearnapp.ui.category.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.tearnsv.tearnapp.repository.TearnRepository
import kotlinx.coroutines.Dispatchers
import kotlin.Exception

class CategoryViewModel(private val repository: TearnRepository) : ViewModel() {

    var idCategory = MutableLiveData("")

    var categoryResponse = idCategory.distinctUntilChanged().switchMap { id ->
        liveData(Dispatchers.IO) {
            try {
                emit(repository.getOneCategory(id))
            } catch (error: Exception) {
                Log.e("CATEGORY_ERROR", error.message.toString())
            }

        }
    }

    fun setIdCategory(id: String) {
        idCategory.value = id
    }
}