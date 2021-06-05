package com.tearnsv.tearnapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TEARN_API_BASE_URL = "https://tearn.herokuapp.com/api/v1/"

private var retrofit = Retrofit.Builder()
    .baseUrl(TEARN_API_BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object TearnAPI{
    val service = retrofit.create(TearnAPIService::class.java)
}

