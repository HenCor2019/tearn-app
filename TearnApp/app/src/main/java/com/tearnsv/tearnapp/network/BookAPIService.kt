package com.tearnsv.tearnapp.network

import com.tearnsv.tearnapp.data.Book
import retrofit2.http.GET
import retrofit2.http.Query

interface BookAPIService {

    @GET("volumes")
    suspend fun getBooks(@Query(value = "q") pattern: String): Book

}