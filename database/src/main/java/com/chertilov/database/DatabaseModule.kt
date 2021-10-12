package com.chertilov.database

import android.content.Context
import androidx.room.Room
import com.chertilov.core_api.database.DogsStorage
import com.chertilov.core_api.database.UsersStorage
import com.chertilov.database.dao.DogsDao
import com.chertilov.database.dao.UserDao
import com.chertilov.database.interfaces.DogsRoomStorage
import com.chertilov.database.interfaces.UsersRoomStorage
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
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()

    @Provides
    @Reusable
    fun provideDogsStorage(dao: DogsDao): DogsStorage = DogsRoomStorage(dao, Dispatchers.IO)

    @Provides
    @Reusable
    fun provideUsersStorage(dao: UserDao): UsersStorage = UsersRoomStorage(dao, Dispatchers.IO)

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