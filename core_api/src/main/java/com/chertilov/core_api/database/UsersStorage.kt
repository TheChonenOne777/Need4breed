package com.chertilov.core_api.database

import com.chertilov.core_api.dto.User
import kotlinx.coroutines.flow.Flow

interface UsersStorage {

    fun getUsers(): Flow<List<User>>

    fun getUser(phoneNumber: String): Flow<User>

    suspend fun replaceUserData(user: User)

    suspend fun saveUser(user: User)
}