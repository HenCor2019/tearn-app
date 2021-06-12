package com.tearnsv.tearnapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BOOK_BASE_URI = "https://www.googleapis.com/books/v1/"

private var retrofit = Retrofit.Builder()
    .baseUrl(BOOK_BASE_URI)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object BookAPI{
    val bookService = retrofit.create(BookAPIService::class.java)
}

