package com.tearnsv.tearnapp.data

data class SearchResponse(
    val error: Boolean,
    val coursesCount: Int,
    val tutorsCount: Int,
    val courses: List<SearchCourse>,
    val tutors: List<SearchTutor>
)

data class SearchCourse(
    var id: String,
    var name: String,
    var tutorsCount: Int
)

data class SearchTutor(
    val id: String,
    val username: String,
    val puntuation: Int,
    val imgUrl: String,
    val subjects: List<String>
)


