package com.tearnsv.tearnapp.data

data class LoginResponse(
    val error: Boolean,
    val accessToken: String,
    val id: String,
    val username: String,
    val email: String,
    val imgUrl: String,
    val isTutor: Boolean,
    val fullName: String,
)

data class User(
    val username: String,
    val email: String,
    val imgUrl: String
)