package com.tearnsv.tearnapp.data.entity

import androidx.room.*

@Entity(tableName = "user")
data class User(
    @PrimaryKey var id : String,
){
    @Ignore lateinit var favTutors : List<String>
    @Ignore var error : Boolean = false
}

@Entity(tableName = "fav_tutors")
data class FavTutor(
    @PrimaryKey var idTutor : String
)

data class UserWithFavTutor(
    @Embedded var user : User,
    @Relation(
        parentColumn = "id",
        entityColumn = "idTutor"
    ) var tutorFavs : List<FavTutor>
)