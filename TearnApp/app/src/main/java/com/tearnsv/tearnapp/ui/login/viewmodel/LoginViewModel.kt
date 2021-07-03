package com.tearnsv.tearnapp.ui.login.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.data.UserGoogle
import com.tearnsv.tearnapp.repository.TearnRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: TearnRepository) : ViewModel() {
    fun saveUser(username: String, imgUrl: String, email: String) {
        viewModelScope.launch {
            try {
                val response = repository.loginWithGoogle(UserGoogle(username, email, imgUrl))
                TearnApplication.prefs.saveId(response.id)
                TearnApplication.prefs.saveAccessToken(response.accessToken)
                TearnApplication.prefs.savePreferences(response.preferences)
                TearnApplication.prefs.saveFavTutors(response.favTutors)
                TearnApplication.prefs.saveIsTutor(response.isTutor)

                Log.d("RESPONSE", response.toString())

            } catch (error: Exception) {
                Log.e("INSERT_ERROR", error.toString())
            }
        }
    }

}