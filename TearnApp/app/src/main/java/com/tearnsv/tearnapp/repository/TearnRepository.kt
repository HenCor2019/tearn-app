package com.tearnsv.tearnapp.repository

import com.tearnsv.tearnapp.data.NewTutor
import com.tearnsv.tearnapp.data.User
import com.tearnsv.tearnapp.network.BookAPI
import com.tearnsv.tearnapp.network.TearnAPI

class TearnRepository(
    private val api: TearnAPI,
    private val bookApi: BookAPI
) {

    //Preferences
    suspend fun findAll() = api.service.getAllCategories()

    //Home
    suspend fun findAllRecommendations(idUser: String) =
        api.service.getAllRecommendations(idUser)

    // Search
    suspend fun getAllSearchResponse(pattern: String) = api.service.getAllSearchResponse(pattern)

    // Course
    suspend fun getOneCourse(id: String) = api.service.getOneCourse(id)

    // Category
    suspend fun getOneCategory(id: String) = api.service.getOneCategory(id)

    //getAllBooks
    suspend fun getBooks(pattern: String) = bookApi.bookService.getBooks(pattern)

    //get one user for account service
    suspend fun getOneUserAccount(id: String) = api.service.getOneUserAccount(id)

    //get favorite tutors from a user
    suspend fun getFavoriteUserFromUser(id: String) = api.service.getFavoriteUserFromUser(id)

    // all subjects
    suspend fun getAllSubjects() = api.service.getAllSubjects()

    // convert to tutor
    suspend fun convertToTutor(newTutor: NewTutor) = api.service.convertToTutor(newTutor)

    // post user
    suspend fun loginWithGoogle(user: User) = api.service.loginWithGoogle(user)

}