package com.chertilov.need4breed.dogs.interactor

import com.chertilov.need4breed.dogs.repo.DogsRepository

class RequestDogsUseCase(private val dogsRepository: DogsRepository) {

    suspend operator fun invoke() = dogsRepository.requestDogs().shuffled()
}