package com.chertilov.need4breed.dogs.repo

import com.chertilov.need4breed.dogs.api.DogsApi
import com.chertilov.need4breed.storage.entities.Dog
import com.chertilov.need4breed.storage.interfaces.DogsStorage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface DogsRepository {

    suspend fun requestDogs()

    fun getDogs(): Flow<List<Dog>>
}

class DogsRepositoryImpl(
        private val dogsApi: DogsApi,
        private val dogsStorage: DogsStorage,
        private val ioDispatcher: CoroutineDispatcher
) : DogsRepository {

    override suspend fun requestDogs() = withContext(ioDispatcher) {
        dogsStorage.saveDogs(dogsApi.requestDogs().message.map { Dog(image = it) })
    }

    override fun getDogs(): Flow<List<Dog>> = dogsStorage.getDogs()
}