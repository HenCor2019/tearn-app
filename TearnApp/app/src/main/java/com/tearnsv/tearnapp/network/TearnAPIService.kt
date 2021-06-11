package com.tearnsv.tearnapp.network

import com.tearnsv.tearnapp.data.*
import retrofit2.http.*

interface TearnAPIService {

    @GET("category/")
    suspend fun getAllCategories() : CategoriesResponse

    @GET("home/{idUser}/")
    suspend fun getAllRecommendations(@Path("idUser") idUser : String) : Recommendations

    @GET("user/tutor/{idTutor}")
    suspend fun getTutor(@Path("idTutor") idTutor : String) : Tutor

    @Headers("Content-Type: application/json")
    @POST("commentary/")
    suspend fun createCommentary(@Body commentary: Commentary): ResponseApi

}