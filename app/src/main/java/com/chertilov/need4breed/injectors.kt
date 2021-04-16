package com.chertilov.need4breed

import android.content.Context
import com.chertilov.need4breed.base.ViewModelFactory
import com.chertilov.need4breed.dogs.api.DogsApi
import com.chertilov.need4breed.dogs.interactor.GetDogsUseCase
import com.chertilov.need4breed.dogs.interactor.RequestDogsUseCase
import com.chertilov.need4breed.dogs.repo.DogsRepository
import com.chertilov.need4breed.dogs.repo.DogsRepositoryImpl
import com.chertilov.need4breed.storage.AppDatabase
import com.chertilov.need4breed.storage.interfaces.DogsRoomStorage
import com.chertilov.need4breed.storage.interfaces.DogsStorage
import kotlinx.coroutines.Dispatchers

object StorageInjectors {

    fun bindDogsStorage(context: Context): DogsStorage =
            DogsRoomStorage.getInstance(AppDatabase.getInstance(context).dogsDao(), Dispatchers.IO)
}

object RepositoryInjectors {

    fun bindDogsRepository(context: Context): DogsRepository =
            DogsRepositoryImpl(DogsApi.getInstance(), StorageInjectors.bindDogsStorage(context), Dispatchers.IO)
}

object UseCaseInjectors {

    fun provideRequestDogsUsecase(context: Context) = RequestDogsUseCase(RepositoryInjectors.bindDogsRepository(context))
    fun provideGetDogsUsecase(context: Context) = GetDogsUseCase(RepositoryInjectors.bindDogsRepository(context))
}

object ViewModelFactoryInjector {

    fun provideDogsViewModel(context: Context) = ViewModelFactory(
            UseCaseInjectors.provideRequestDogsUsecase(context),
            UseCaseInjectors.provideGetDogsUsecase(context)
    )
}