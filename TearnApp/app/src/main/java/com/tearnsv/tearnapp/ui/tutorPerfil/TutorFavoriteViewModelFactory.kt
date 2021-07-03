package com.tearnsv.tearnapp.ui.tutorPerfil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tearnsv.tearnapp.repository.TearnRepository

class TutorFavoriteViewModelFactory(private val repository: TearnRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TutorFavoriteViewModel::class.java)) {
            return TutorFavoriteViewModel(repository) as T
        }
        throw Exception("Se desconoce el tipo de viewmodel")
    }
}