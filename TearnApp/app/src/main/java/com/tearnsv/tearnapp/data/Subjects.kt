package com.tearnsv.tearnapp.data

data class AllSubject(
    val error: Boolean,
    val subjectCount: Int,
    val results: List<SubjectName>
)

data class SubjectName(
    val id: String,
    val name: String,
    val courses: List<CourseResponse>
)

