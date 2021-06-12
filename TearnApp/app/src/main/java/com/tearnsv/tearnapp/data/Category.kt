package com.tearnsv.tearnapp.data

data class CategoryResponse(
    val error: Boolean,
    val id: String,
    val name: String,
    val imgUrl: String,
    val description: String,
    val subjectCount: Int,
    val subjects: List<SubjectResponse>
)

data class SubjectResponse(
    val _id: String,
    val name: String,
    val courses: List<CourseResponse>
)

data class CourseResponse(
    val _id: String,
    val name: String
)