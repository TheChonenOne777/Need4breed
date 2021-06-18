package com.chertilov.database

import android.content.Context
import androidx.room.Room
import com.chertilov.core_api.database.DogsStorage
import com.chertilov.database.dao.DogsDao
import com.chertilov.database.interfaces.DogsRoomStorage
import dagger.Module
import dagger.Provides
import dagger.Reusable
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Reusable
    fun provideDogsDao(database: AppDatabase): DogsDao = database.dogsDao()

    @Provides
    @Reusable
    fun provideDogsStorage(dao: DogsDao): DogsStorage = DogsRoomStorage(dao, Dispatchers.IO)

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "need4breed_database"
        )
            .fallbackToDestructiveMigration()
            .build()
}