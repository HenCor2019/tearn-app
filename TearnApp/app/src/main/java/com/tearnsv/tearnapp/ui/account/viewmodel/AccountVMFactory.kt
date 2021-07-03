package com.tearnsv.tearnapp.ui.account.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tearnsv.tearnapp.repository.TearnRepository

class AccountVMFactory(private val repository: TearnRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountViewModel::class.java))
            return AccountViewModel(repository) as T
        throw Exception("Unknow view model type")
    }
}