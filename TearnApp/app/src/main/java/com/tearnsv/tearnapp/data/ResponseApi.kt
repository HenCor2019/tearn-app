package com.tearnsv.tearnapp.data

data class ResponseApi (
    var error: Boolean,
    var message: String
)

data class FavTutorResponse(
    var error: Boolean,
    var message: String,
    var favTutors : List<String>
)