package com.tearnsv.tearnapp.data

data class FavoriteTutor(
    val error: Boolean,
    val tutorCount: Int,
    val favTutors: List<Tutor>
)