package com.chertilov.need4breed.storage.interfaces

import com.chertilov.need4breed.storage.dao.DogsDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface DogsStorage {

    fun getDogs(): Flow<List<String>>

    suspend fun saveDogs(dogs: List<String>)
}

class DogsRoomStorage private constructor(
        private val dogsDao: DogsDao,
        private val ioDispatcher: CoroutineDispatcher
) : DogsStorage {

    override fun getDogs(): Flow<List<String>> = dogsDao.getAll()

    override suspend fun saveDogs(dogs: List<String>) = withContext(ioDispatcher) {
        dogsDao.insert(dogs)
    }

    companion object {
        private var instance: DogsRoomStorage? = null

        fun getInstance(dogsDao: DogsDao, ioDispatcher: CoroutineDispatcher) = instance
                ?: DogsRoomStorage(dogsDao, ioDispatcher).also { instance = it }
    }
}