package com.chertilov.dogs.di

import androidx.lifecycle.ViewModel
import com.chertilov.core_api.viewmodel.EagerTrigger
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
        fun provideDogsViewModel(
            map: @JvmSuppressWildcards MutableMap<Class<out ViewModel>, ViewModel>,
            interactor: DogsInteractor
        ): ViewModel = DogsViewModel(interactor).also {
            map[DogsViewModel::class.java] = it
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideDummy(viewModel: ViewModel) = EagerTrigger()
    }
}

//class EagerTrigger