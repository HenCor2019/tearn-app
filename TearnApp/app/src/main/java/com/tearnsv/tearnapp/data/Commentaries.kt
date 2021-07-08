package com.tearnsv.tearnapp.data

data class Commentaries(
  val description: String,
  val puntuation: Int,
  val author: Author
)

data class Commentary(
  val id: String? = null,
  val author: String? = null,
  val description: String? = null,
  val puntuation: Int? = 0,
  val adressedId: String? = null,
)


