package com.tearnsv.tearnapp.network

import com.tearnsv.tearnapp.data.*
import com.tearnsv.tearnapp.data.entity.User
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

    @Headers("Content-Type: application/json")
    @POST("report/")
    suspend fun createReport(@Body report: Report): ResponseApi

    @GET("search")
    suspend fun getAllSearchResponse(@Query(value = "pattern") pat: String): SearchResponse

    @GET("course/{id}")
    suspend fun getOneCourse(@Path("id") id: String): Course

    @GET("category/{id}")
    suspend fun getOneCategory(@Path("id") id: String): CategoryResponse

    @GET("user/{id}")
    suspend fun getUser(@Path("id") id: String): User

    @PUT("user/")
    suspend fun addOrRemoveFavTutor(@Body favTutor : FavTutorPetition) : FavTutorResponse

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