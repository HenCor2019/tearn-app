package com.tearnsv.tearnapp.data

data class Account(
    val error: Boolean,
    val id: String,
    val username: String,
    val isTutor: Boolean,
    val imgUrl: String,
    val email: String,
)
