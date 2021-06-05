package com.tearnsv.tearnapp.data

data class Recommendations(
    var error : Boolean,
    var subjects: List<Subjects>,
    var tutors: List<Tutors>
)

data class Subjects(
    var id: String ,
    var name : String,
    var url : String ,
    var courseCount : Int
)

data class Tutors(
    var fullName: String,
    var imgUrl : String,
    var puntuation : Int,
    var url : String,
    var subjects: List<String>
)