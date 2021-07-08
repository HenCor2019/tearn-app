package com.tearnsv.tearnapp.data

data class SearchResponse(
    val error: Boolean,
    val coursesCount: Int,
    val tutorsCount: Int,
    val courses: List<SearchCourse>,
    val tutors: List<TutorSearch>
)

data class SearchCourse(
    var id: String,
    var name: String,
    var tutorsCount: Int
)



