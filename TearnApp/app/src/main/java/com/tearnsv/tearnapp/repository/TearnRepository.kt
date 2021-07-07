package com.tearnsv.tearnapp.repository

import android.util.Log
import com.tearnsv.tearnapp.data.*
import com.tearnsv.tearnapp.data.dao.TearnDao
import com.tearnsv.tearnapp.data.entity.FavTutor
import com.tearnsv.tearnapp.data.UserGoogle
import com.tearnsv.tearnapp.data.entity.User
import com.tearnsv.tearnapp.data.entity.UserWithFavTutor
import com.tearnsv.tearnapp.network.BookAPI
import com.tearnsv.tearnapp.network.TearnAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TearnRepository(
    private val api : TearnAPI,
    private val dao : TearnDao,
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

    suspend fun updateUserCommentary(commentary: Commentary) = api.service.updateUserCommentary(commentary)

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

    // Save user when the app start
    suspend fun saveUser(id: String, favs: String) = withContext(Dispatchers.IO){
        var user = dao.getUser()
        if(user == null){
            val favTutor = favs.split(",")
            Log.e("favs",favs)
            Log.e("favTutor",favTutor.toString())
            val favTutorArray = mutableListOf<FavTutor>()
            favTutor.forEach {
                favTutorArray.add(FavTutor(it))
            }
            Log.e("favTutorArray",favTutorArray.toString())
            var newUser = UserWithFavTutor(User(id),favTutorArray.toList())
            dao.insert(newUser)
        }
    }

    // Get all tutors
    fun getFavTutors() = dao.getFavTutors()

    // Add or remove tutors favs
    suspend fun addOrRemoveFavTutor(favTutor: FavTutorPetition) = withContext(Dispatchers.IO){
        var response = api.service.addOrRemoveFavTutor(favTutor)
        if(!response.error){
            val favTutors = mutableListOf<FavTutor>()
            response.favTutors.forEach {
                favTutors.add(FavTutor(it))
            }
            if (response.favTutors.contains(favTutor.favTutor)){
                dao.insertFavTutors(favTutors.toList())
            } else dao.deleteFavTutor(favTutor.favTutor!!)
        }
        response
    }

    suspend fun updateUser(user : FavTutorPetition) = withContext(Dispatchers.IO){
        var response = api.service.updateUser(user)
        if(response.error){
            Log.e("error",response.error.toString())
        }
        response
    }

    //delete all tables from database
    suspend fun deleteDatabase() = dao.deleteDatabase()

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
    suspend fun loginWithGoogle(user: UserGoogle) = api.service.loginWithGoogle(user)

}