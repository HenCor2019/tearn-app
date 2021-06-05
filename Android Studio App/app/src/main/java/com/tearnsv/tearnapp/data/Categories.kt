package com.tearnsv.tearnapp.data

data class CategoriesResponse (
    var error: Boolean,
    var count: Int,
    var results: List<Category>
)

data class Category(
    var id: String,
    var name: String,
    var isPreference: Boolean = false
)

data class Preference(
    var id: String,
    var name: String,
)