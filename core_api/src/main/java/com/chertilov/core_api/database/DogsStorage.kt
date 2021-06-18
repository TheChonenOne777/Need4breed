package com.chertilov.core_api.database

import com.chertilov.core_api.dto.Dog
import kotlinx.coroutines.flow.Flow

interface DogsStorage {

    fun getDogs(): Flow<List<Dog>>

    suspend fun saveDogs(dogs: List<Dog>)
}