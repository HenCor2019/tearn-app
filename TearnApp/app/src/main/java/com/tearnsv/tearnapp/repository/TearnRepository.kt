package com.tearnsv.tearnapp.repository

import com.tearnsv.tearnapp.data.Commentary
import com.tearnsv.tearnapp.data.Report
import com.tearnsv.tearnapp.data.ResponseApi
import com.tearnsv.tearnapp.network.TearnAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TearnRepository(
    private val api : TearnAPI
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
}