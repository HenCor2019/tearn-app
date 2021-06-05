package com.tearnsv.tearnapp.network

import com.tearnsv.tearnapp.data.CategoriesResponse
import com.tearnsv.tearnapp.data.Recommendations
import retrofit2.http.GET
import retrofit2.http.Path

interface TearnAPIService {

    @GET("category/")
    suspend fun getAllCategories() : CategoriesResponse

    @GET("home/{idUser}/")
    suspend fun getAllRecommendations(@Path("idUser") idUser : String) : Recommendations

}