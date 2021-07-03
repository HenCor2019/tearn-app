package com.tearnsv.tearnapp.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tearnsv.tearnapp.repository.TearnRepository

class LoginVMFactory(private val repository: TearnRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if( modelClass.isAssignableFrom(LoginViewModel::class.java))
            return LoginViewModel(repository) as T
        throw Exception("Unknown viewModel type")
    }
}