package com.chertilov.database.interfaces

import com.chertilov.core_api.database.UsersStorage
import com.chertilov.core_api.dto.User
import com.chertilov.database.dao.UserDao
import com.chertilov.database.toEntity
import com.chertilov.database.toStorage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject


class UsersRoomStorage @Inject constructor(
    private val userDao: UserDao,
    private val ioDispatcher: CoroutineDispatcher
) : UsersStorage {

    override fun getUsers(): Flow<List<User>> = userDao.getAll()
        .map { it.map { it.toEntity() } }

    override fun getUser(phoneNumber: String): Flow<User> = userDao.get(phoneNumber)
        .map { it.toEntity() }

    override suspend fun addMatch(user: User) = withContext(ioDispatcher) {
        userDao.updateMatch(user.toStorage())
    }

    override suspend fun saveUser(user: User) = withContext(ioDispatcher) {
        userDao.insert(user.toStorage())
    }
}