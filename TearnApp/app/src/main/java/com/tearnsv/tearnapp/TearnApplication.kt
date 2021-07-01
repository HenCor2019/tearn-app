package com.tearnsv.tearnapp

import android.app.Application
import com.tearnsv.tearnapp.network.BookAPI
import com.tearnsv.tearnapp.network.TearnAPI
import com.tearnsv.tearnapp.repository.TearnRepository


class TearnApplication : Application() {
    val tearnRepository by lazy {
        TearnRepository(TearnAPI, BookAPI)
    }

    companion object {
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()

        prefs = Prefs(applicationContext)
    }
}