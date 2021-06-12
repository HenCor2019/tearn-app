package com.tearnsv.tearnapp.data

data class NewTutor(
    val id: String,
    val coursesId: List<String>,
    val subjectsId: List<String>,
    val fullName: String,
    val dot: String,
    val description: String,
    val responseTime: String,
    val availability: List<Int>,
    val languages: List<String> = listOf("Español") ,
    val puntuation: Int = 0,
)
