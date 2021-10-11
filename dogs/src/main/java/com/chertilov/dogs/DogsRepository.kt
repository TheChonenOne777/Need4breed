package com.chertilov.dogs

import com.chertilov.core_api.database.DogsStorage
import com.chertilov.core_api.dto.Dog
import com.chertilov.core_api.network.DogsNetwork
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface DogsRepository {

    suspend fun requestDogs()

    fun getDogs(): Flow<List<Dog>>
}

class DogsRepositoryImpl @Inject constructor(
    private val dogsNetwork: DogsNetwork,
    private val dogsStorage: DogsStorage
) : DogsRepository {

    override suspend fun requestDogs() = dogsStorage.saveDogs(dogsNetwork.requestDogs())

    override fun getDogs(): Flow<List<Dog>> = dogsStorage.getDogs()
}