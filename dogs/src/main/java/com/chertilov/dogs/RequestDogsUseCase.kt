package com.chertilov.dogs

import com.chertilov.core_api.dto.Dog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RequestDogsUseCase(private val dogsRepository: DogsRepository) {

    suspend operator fun invoke() = dogsRepository.requestDogs()
}

class GetDogsUseCase(private val dogsRepository: DogsRepository) {

    operator fun invoke(): Flow<List<Dog>> = dogsRepository.getDogs().map { it.shuffled() }
}