package com.tearnsv.tearnapp.data

data class Course(
    val error: Boolean,
    val name: String,
    val imgUrl: String,
    val tutorsCount: Int,
    val tutors: List<TutorFromCourse>
)

data class TutorFromCourse(
    val id: String,
    val subjects: List<String>,
    val puntuation: Int,
    val imgUrl: String,
    val fullName: String,
)