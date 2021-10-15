package com.chertilov.dogs.di

import androidx.lifecycle.ViewModelProvider
import com.chertilov.core_api.base.ViewModelFactory
import com.chertilov.dogs.DogsInteractor
import com.chertilov.dogs.DogsRepository
import com.chertilov.dogs.DogsRepositoryImpl
import com.chertilov.dogs.DogsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DogsModule {

    @Binds
    @Singleton
    abstract fun bindDogsRepository(repo: DogsRepositoryImpl): DogsRepository

    @Module
    companion object {

        @Provides
        @Singleton
        @JvmStatic
        fun bindsFactory(interactor: DogsInteractor): ViewModelProvider.Factory {
            return ViewModelFactory(
                mutableMapOf(
                    DogsViewModel::class.java to DogsViewModel(interactor)
                )
            )
        }
    }
}
