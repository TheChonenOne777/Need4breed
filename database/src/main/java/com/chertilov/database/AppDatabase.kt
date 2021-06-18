package com.chertilov.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chertilov.database.dao.DogsDao
import com.chertilov.database.entities.StorageDog


@Database(
    entities = [
        StorageDog::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dogsDao(): DogsDao
}