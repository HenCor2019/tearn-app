package com.tearnsv.tearnapp.data

data class Tutor (
    var error: Boolean,
    var fullName: String?,
    var imgUrl: String?,
    var description: String?,
    var puntuation: Int?,
    var languages: List<String>?,
    var commentaries: List<Commentaries>?,
    var availability: List<String>?,
    var subjects: List<String>?,
    var responseTime: String?,
    var active: Boolean?
    )

data class Author(
    var username: String,
    var imgUrl: String
)

data class Tutors(
    var id: String,
    var fullName: String,
    var imgUrl : String,
    var puntuation : Int,
    var url : String,
    var subjects: List<String>
)