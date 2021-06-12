package com.tearnsv.tearnapp.ui.categories.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.tearnsv.tearnapp.repository.TearnRepository
import kotlinx.coroutines.Dispatchers

class CategoryViewModel(private val repository: TearnRepository): ViewModel() {

    val fetchCategoryResponse = liveData(Dispatchers.IO){
        try {
            emit(repository.findAll())
        }catch (error: Exception){
            Log.e("CATEGORY_ERROR", error.message.toString())
        }
    }
}