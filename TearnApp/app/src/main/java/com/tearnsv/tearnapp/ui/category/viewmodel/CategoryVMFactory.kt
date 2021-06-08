package com.tearnsv.tearnapp.ui.category.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tearnsv.tearnapp.repository.TearnRepository
import com.tearnsv.tearnapp.ui.category.viewmodel.CategoryViewModel

class CategoryVMFactory(private val repository: TearnRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(repository) as T
        }
        throw Exception("Se desconoce el tipo de viewmodel")
    }

}