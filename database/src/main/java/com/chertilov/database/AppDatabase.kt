package com.chertilov.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chertilov.database.dao.DogsDao
import com.chertilov.database.dao.UserDao
import com.chertilov.database.entities.StorageDog
import com.chertilov.database.entities.StorageUser


@Database(
    entities = [
        StorageDog::class,
        StorageUser::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dogsDao(): DogsDao
    abstract fun userDao(): UserDao
}