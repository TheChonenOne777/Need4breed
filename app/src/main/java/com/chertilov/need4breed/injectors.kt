package com.chertilov.need4breed

import com.chertilov.need4breed.base.ViewModelFactory
import com.chertilov.need4breed.dogs.api.DogsApi
import com.chertilov.need4breed.dogs.interactor.RequestDogsUseCase
import com.chertilov.need4breed.dogs.repo.DogsRepository
import com.chertilov.need4breed.dogs.repo.DogsRepositoryImpl
import kotlinx.coroutines.Dispatchers


object RepositoryInjectors {

    fun bindDogsRepository(): DogsRepository =
        DogsRepositoryImpl(DogsApi.getInstance(), Dispatchers.IO)
}

object UseCaseInjectors {

    fun provideRequestDogsUsecase() = RequestDogsUseCase(RepositoryInjectors.bindDogsRepository())
}

object ViewModelFactoryInjector {

    fun provideDogsViewModel() = ViewModelFactory(UseCaseInjectors.provideRequestDogsUsecase())
}