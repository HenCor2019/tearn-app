package com.tearnsv.tearnapp.repository

import com.tearnsv.tearnapp.data.Commentary
import com.tearnsv.tearnapp.data.FavTutorPetition
import com.tearnsv.tearnapp.data.Report
import com.tearnsv.tearnapp.data.ResponseApi
import com.tearnsv.tearnapp.data.dao.TearnDao
import com.tearnsv.tearnapp.data.entity.FavTutor
import com.tearnsv.tearnapp.data.entity.User
import com.tearnsv.tearnapp.data.entity.UserWithFavTutor
import com.tearnsv.tearnapp.network.TearnAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TearnRepository(
    private val api : TearnAPI,
    private val dao : TearnDao
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

    suspend fun getUser(id: String) = withContext(Dispatchers.IO){
        var user = dao.getUser()
        if(user.id.isNullOrEmpty()){
            val newUser = api.service.getUser(id)
            val favTutor = mutableListOf<FavTutor>()
            newUser.favTutors.forEach {
                favTutor.add(FavTutor(it))
            }
            var userWithFavTutor = UserWithFavTutor(newUser, favTutor.toList())
            dao.insert(userWithFavTutor)
        }
        user
    }

    fun getFavTutors() = dao.getFavTutors()

    suspend fun addOrRemoveFavTutor(favTutor: FavTutorPetition) = withContext(Dispatchers.IO){
        var response = api.service.addOrRemoveFavTutor(favTutor)
        if(!response.error){
            val favTutors = mutableListOf<FavTutor>()
            response.favTutors.forEach {
                favTutors.add(FavTutor(it))
            }
            if (response.favTutors.contains(favTutor.favTutor)){
                dao.insertFavTutors(favTutors.toList())
            } else dao.deleteFavTutor(favTutor.favTutor)
        }
        response
    }
}