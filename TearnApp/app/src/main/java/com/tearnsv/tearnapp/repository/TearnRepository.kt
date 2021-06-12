package com.tearnsv.tearnapp.repository

import com.tearnsv.tearnapp.data.Commentary
import com.tearnsv.tearnapp.data.Report
import com.tearnsv.tearnapp.data.ResponseApi
import com.tearnsv.tearnapp.data.NewTutor
import com.tearnsv.tearnapp.data.User
import com.tearnsv.tearnapp.network.BookAPI
import com.tearnsv.tearnapp.network.TearnAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TearnRepository(
    private val api: TearnAPI,
    private val bookApi: BookAPI
) {

    //Preferences
    suspend fun findAll() = api.service.getAllCategories()

    //Home
    suspend fun findAllRecommendations(idUser : String) =
    api.service.getAllRecommendations(idUser)

    //TutorProfile
    suspend fun getTutor(idTutor : String) =
        api.service.getTutor(idTutor)

    suspend fun createCommentary(commentary: Commentary) = withContext(Dispatchers.IO) {
        var response = api.service.createCommentary(commentary)
        response
    }

    suspend fun createReport(report: Report) = withContext(Dispatchers.IO){
        var response = api.service.createReport(report)
        response
    }

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