package com.tearnsv.tearnapp

import android.app.Application
import com.tearnsv.tearnapp.data.TearnDatabase
import com.tearnsv.tearnapp.network.TearnAPI
import com.tearnsv.tearnapp.repository.TearnRepository


class TearnApplication : Application(){
    private val database by lazy { TearnDatabase.getDatabase(this) }
    val tearnRepository by lazy {
        val tearnDao = database.getTearnDao()
        TearnRepository(TearnAPI,tearnDao)
    }
}