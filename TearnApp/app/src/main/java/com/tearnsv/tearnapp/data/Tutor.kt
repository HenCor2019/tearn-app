package com.tearnsv.tearnapp.data

import com.google.gson.annotations.SerializedName

data class Tutor(
  var error: Boolean,
  var fullName: String?,
  var imgUrl: String?,
  var description: String?,
  val id: String,
  val username: String?,
  var puntuation: Int?,
  var languages: List<String>?,
  var commentaries: List<Commentaries>?,
  var availability: List<String>?,
  @SerializedName("subjectsId")
  var subjects: List<String>?,
  @SerializedName("coursesId")
  val courses: List<String>?,
  var responseTime: String?,
  @SerializedName("dot")
  val dod: String,
  var active: Boolean?
)

data class Author(
  var username: String,
  var imgUrl: String
)

data class Tutors(
  var id: String,
  var fullName: String,
  var imgUrl: String,
  var puntuation: Int,
  var url: String,
  var subjects: List<String>
)