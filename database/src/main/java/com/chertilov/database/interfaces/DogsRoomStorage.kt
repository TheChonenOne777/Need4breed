package com.chertilov.database.interfaces

import com.chertilov.core_api.database.DogsStorage
import com.chertilov.core_api.dto.Dog
import com.chertilov.database.dao.DogsDao
import com.chertilov.database.toEntity
import com.chertilov.database.toStorage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogsRoomStorage @Inject constructor(
    private val dogsDao: DogsDao,
    private val ioDispatcher: CoroutineDispatcher
) : DogsStorage {

    override fun getDogs(): Flow<List<Dog>> = dogsDao.getAll()
        .map { it.map { it.toEntity() } }

    override suspend fun saveDogs(dogs: List<Dog>) = withContext(ioDispatcher) {
        dogsDao.insert(dogs.map { it.toStorage() })
    }

    companion object {
        private var instance: DogsRoomStorage? = null

        fun getInstance(dogsDao: DogsDao, ioDispatcher: CoroutineDispatcher) = instance
            ?: DogsRoomStorage(dogsDao, ioDispatcher).also { instance = it }
    }
}