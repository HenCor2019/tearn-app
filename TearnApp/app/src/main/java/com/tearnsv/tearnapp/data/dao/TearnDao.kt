package com.tearnsv.tearnapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tearnsv.tearnapp.data.entity.FavTutor
import com.tearnsv.tearnapp.data.entity.User
import com.tearnsv.tearnapp.data.entity.UserWithFavTutor

@Dao
interface TearnDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavTutors(favTutor: List<FavTutor>)

    @Transaction
    suspend fun insert(userWithFavTutor: UserWithFavTutor){
        insertUser(userWithFavTutor.user)
        insertFavTutors(userWithFavTutor.tutorFavs)
    }

    @Transaction
    @Query("SELECT * FROM user")
    suspend fun getUser(): User?

    @Query("SELECT * FROM fav_tutors")
    fun getFavTutors(): LiveData<List<FavTutor>>

    @Query("DELETE FROM fav_tutors WHERE idTutor = :idTutor")
    suspend fun deleteFavTutor(idTutor: String)

    @Transaction
    suspend fun deleteDatabase(){
        deleteUserTable()
        deleteFavTutorsTable()
    }

    @Query("DELETE FROM user")
    suspend fun deleteUserTable()

    @Query("DELETE FROM fav_tutors")
    suspend fun deleteFavTutorsTable()
}