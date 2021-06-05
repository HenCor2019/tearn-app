package com.tearnsv.tearnapp.repository

import com.tearnsv.tearnapp.network.TearnAPI

class TearnRepository(
    private val api : TearnAPI
) {

    //Preferences
    suspend fun findAll() = api.service.getAllCategories()

    //Home
    suspend fun findAllRecommendations(idUser : String) =
    api.service.getAllRecommendations(idUser)
}