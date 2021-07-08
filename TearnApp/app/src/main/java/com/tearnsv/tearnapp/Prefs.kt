package com.tearnsv.tearnapp

import android.content.Context

class Prefs(val context: Context) {

    val SHARED_NAME = "SHARED_NAME"
    val ID_LOGIN_USER = "ID_LOGIN_USER"
    val IS_TUTOR = "IS_TUTOR"
    val ACCESS_TOKEN = "ACCESS_TOKEN"
    val PREFERENCES = "PREFERENCES"
    val FAV_TUTORS = "FAV_TUTORS"

    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveId(id: String) {
        storage.edit().putString(ID_LOGIN_USER, id).apply()
    }

    fun getId() = storage.getString(ID_LOGIN_USER, "")

    fun wipe() {
        storage.edit().clear().apply()
    }

    fun saveIsTutor(isTutor: Boolean) {
        storage.edit().putBoolean(IS_TUTOR, isTutor).apply()
    }

    fun savePreferences(preferences: List<String>) {
        if (preferences.isEmpty()) {
            storage.edit().putString(PREFERENCES, "").apply()
            return
        }
        storage.edit().putString(PREFERENCES, preferences.reduce { acc, pref -> "$acc,$pref" })
            .apply()
    }

    fun saveFavTutors(favTutors: List<String>) {
        if (favTutors.isEmpty()) {
            storage.edit().putString(FAV_TUTORS, "").apply()
            return
        }
        storage.edit().putString(FAV_TUTORS, favTutors.reduce { acc, tutor -> "$acc,$tutor" })
            .apply()
    }

    fun saveAccessToken(accessToken: String) {
        storage.edit().putString(ACCESS_TOKEN, accessToken).apply()
    }

    fun getIsTutor() = storage.getBoolean(IS_TUTOR, false)
    fun getAccessToken() = storage.getString(ACCESS_TOKEN, "")
    fun getPreferences() = storage.getString(PREFERENCES, "")
    fun getFavTutors() = storage.getString(FAV_TUTORS, "")


}