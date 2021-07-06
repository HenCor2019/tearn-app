package com.tearnsv.tearnapp.data
import com.tearnsv.tearnapp.data.entity.User

data class FavTutorPetition(
    var id: String,
    var favTutor: String? = null,
    var preferences: List<String>? = null
)