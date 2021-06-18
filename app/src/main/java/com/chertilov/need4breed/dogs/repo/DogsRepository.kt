package com.chertilov.need4breed.dogs.repo

import com.chertilov.core_api.database.DogsStorage
import com.chertilov.core_api.dto.Dog
import com.chertilov.core_api.network.DogsNetwork
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface DogsRepository {

    suspend fun requestDogs()

    fun getDogs(): Flow<List<Dog>>
}

class DogsRepositoryImpl(
    private val dogsNetwork: DogsNetwork,
    private val dogsStorage: DogsStorage,
    private val ioDispatcher: CoroutineDispatcher
) : DogsRepository {

    override suspend fun requestDogs() = withContext(ioDispatcher) {
        dogsStorage.saveDogs(dogsNetwork.requestDogs())
    }

    override fun getDogs(): Flow<List<Dog>> = dogsStorage.getDogs()
}