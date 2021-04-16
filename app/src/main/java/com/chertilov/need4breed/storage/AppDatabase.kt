package com.chertilov.need4breed.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chertilov.need4breed.storage.dao.DogsDao
import com.chertilov.need4breed.storage.entities.Dog


@Database(
        entities = [
            Dog::class
        ],
        version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dogsDao(): DogsDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context) = instance
                ?: Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "need4breed_database")
                        .fallbackToDestructiveMigration()
                        .build()
                        .also { instance = it }
    }
}