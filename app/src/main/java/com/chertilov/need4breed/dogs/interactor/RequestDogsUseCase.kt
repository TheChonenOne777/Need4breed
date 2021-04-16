package com.chertilov.need4breed.dogs.interactor

import com.chertilov.need4breed.dogs.repo.DogsRepository
import com.chertilov.need4breed.storage.entities.Dog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RequestDogsUseCase(private val dogsRepository: DogsRepository) {

    suspend operator fun invoke() = dogsRepository.requestDogs()
}

class GetDogsUseCase(private val dogsRepository: DogsRepository) {

    operator fun invoke(): Flow<List<Dog>> = dogsRepository.getDogs().map { it.shuffled() }
}