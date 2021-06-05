package com.tearnsv.tearnapp.network

import com.tearnsv.tearnapp.data.CategoriesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TearnAPIService {

    @GET("category/")
    suspend fun getAllCategories() : CategoriesResponse

}