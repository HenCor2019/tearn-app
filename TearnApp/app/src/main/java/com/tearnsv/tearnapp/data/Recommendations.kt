package com.tearnsv.tearnapp.data

data class Recommendations(
    var error : Boolean,
    var tutors: List<Tutors>,
    var courses: List<CoursePreferences>
)

data class Subjects(
    var id: String ,
    var name : String,
    var url : String ,
    var courseCount : Int
)

data class CoursePreferences(
    var id: String ,
    var name : String,
    var tutorCount : Int
)
