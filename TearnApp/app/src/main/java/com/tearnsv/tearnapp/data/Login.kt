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
    val favTutors: List<String>,
    val preferences: List<String>
)

data class UserGoogle(
    val username: String,
    val email: String,
    val imgUrl: String
)