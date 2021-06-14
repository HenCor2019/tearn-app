package com.tearnsv.tearnapp.data

data class NewTutor(
  val id: String,
  val coursesId: List<String>? = null,
  val subjectsId: List<String>? = null,
  val fullName: String,
  val dot: String? = null,
  val description: String,
  val responseTime: String,
  val availability: List<String>? = null,
  val languages: List<String>? = null,
  val puntuation: Int? = null,
  val active: Boolean? = null,
)
