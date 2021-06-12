package com.tearnsv.tearnapp.network

import com.tearnsv.tearnapp.data.*
import retrofit2.http.*

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

    @GET("user/{id}")
    suspend fun getOneUserAccount(@Path("id") id: String): Account

    @GET("user/tutor/user/{id}")
    suspend fun getFavoriteUserFromUser(@Path("id") id: String): FavoriteTutor

    @GET("subject/")
    suspend fun getAllSubjects(): AllSubject

    // PUT SERVICES
    @PUT("user/tutor/")
    suspend fun convertToTutor(@Body newTutor: NewTutor)

    // POST SERVICES
    @POST("user/login")
    suspend fun loginWithGoogle(@Body user: User): LoginResponse

}