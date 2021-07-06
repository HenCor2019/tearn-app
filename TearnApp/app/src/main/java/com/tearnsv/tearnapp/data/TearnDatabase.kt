package com.tearnsv.tearnapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tearnsv.tearnapp.data.dao.TearnDao
import com.tearnsv.tearnapp.data.entity.FavTutor
import com.tearnsv.tearnapp.data.entity.User

@Database(entities = [User::class, FavTutor::class], version = 1, exportSchema = true)
abstract class TearnDatabase : RoomDatabase(){
    abstract fun getTearnDao() : TearnDao

    companion object {
        @Volatile
        private var INSTANCE: TearnDatabase? = null

        fun getDatabase(context: Context): TearnDatabase {
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    TearnDatabase::class.java, "tearn_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }


    }
}