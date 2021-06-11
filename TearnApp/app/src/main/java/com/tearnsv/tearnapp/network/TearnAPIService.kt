package com.tearnsv.tearnapp.network

import com.tearnsv.tearnapp.data.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TearnAPIService {

    @GET("category/")
    suspend fun getAllCategories() : CategoriesResponse

    @GET("home/{idUser}/")
    suspend fun getAllRecommendations(@Path("idUser") idUser : String) : Recommendations

    @GET("search")
    suspend fun getAllSearchResponse(@Query(value = "pattern") pat: String): SearchResponse

    @GET("course/{id}")
    suspend fun getOneCourse(@Path("id") id: String): Course

    @GET("category/{id}")
    suspend fun getOneCategory(@Path("id") id: String): CategoryResponse

}