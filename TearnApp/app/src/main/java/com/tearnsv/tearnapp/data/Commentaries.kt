package com.tearnsv.tearnapp.data

data class Commentaries(
    var description: String,
    var puntuation: Int,
    var author: Author
)

data class Commentary(
    var author: String,
    var description: String,
    var puntuation: Int,
    var adressedId: String
)