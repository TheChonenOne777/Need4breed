package com.chertilov.need4breed.dogs.repo

import com.chertilov.need4breed.dogs.api.DogsApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface DogsRepository {

    suspend fun requestDogs(): List<String>
}

class DogsRepositoryImpl(
        private val dogsApi: DogsApi,
        private val ioDispatcher: CoroutineDispatcher
) : DogsRepository {

    override suspend fun requestDogs() = withContext(ioDispatcher) {
        dogsApi.requestDogs().message
    }
}