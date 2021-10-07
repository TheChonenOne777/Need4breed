package com.chertilov.dogs

import com.chertilov.core_api.dto.Dog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DogsInteractor @Inject constructor(private val dogsRepository: DogsRepository) {

    suspend fun requestDogs() = dogsRepository.requestDogs()

    fun getDogs(): Flow<List<Dog>> = dogsRepository.getDogs().map { it.shuffled() }
}
