package com.tearnsv.tearnapp.ui.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tearnsv.tearnapp.repository.TearnRepository
import com.tearnsv.tearnapp.ui.preferences.PreferencesViewModel

class PreferencesViewModelFactory(private val repository: TearnRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PreferencesViewModel::class.java)) {
            return PreferencesViewModel(repository) as T
        }
        throw Exception("Se desconoce el tipo de viewmodel")
    }
}

