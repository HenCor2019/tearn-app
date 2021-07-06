package com.tearnsv.tearnapp.ui.login.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.data.UserGoogle
import com.tearnsv.tearnapp.data.entity.User
import com.tearnsv.tearnapp.repository.TearnRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: TearnRepository) : ViewModel() {
    var favTutors = MutableLiveData("")
    var ID = MutableLiveData("")
    var preferences = MutableLiveData("")

    suspend fun saveUser(username: String, imgUrl: String, email: String) {
        try {
            val response = repository.loginWithGoogle(UserGoogle(username, email, imgUrl))
            TearnApplication.prefs.saveId(response.id)
            Log.e("id", response.id)
            TearnApplication.prefs.saveAccessToken(response.accessToken)
            TearnApplication.prefs.savePreferences(response.preferences)
            Log.e("preferences", response.preferences.toString())
            TearnApplication.prefs.saveFavTutors(response.favTutors)
            Log.e("favTutor", response.favTutors.toString())
            TearnApplication.prefs.saveIsTutor(response.isTutor)
            Log.e("isTutor", response.isTutor.toString())
            saveUserRoom()

        } catch (error: Exception) {
            Log.e("INSERT_ERROR", error.toString())
        }
    }

    private fun saveUserRoom() {
        viewModelScope.launch {
            try {
                ID.value = TearnApplication.prefs.getId()
                favTutors.value = TearnApplication.prefs.getFavTutors()
                repository.saveUser(ID.value!!, favTutors.value!!)
                preferences.value = TearnApplication.prefs.getPreferences()
            } catch (error: Exception) {
                Log.e("room_error", error.toString())
            }
        }
    }


}