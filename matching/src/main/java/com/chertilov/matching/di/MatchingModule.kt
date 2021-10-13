package com.chertilov.matching.di

import androidx.lifecycle.ViewModel
import com.chertilov.matching.MatchingInteractor
import com.chertilov.matching.MatchingViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
abstract class MatchingModule {

    @Module
    companion object {

        @Provides
        @Singleton
        @JvmStatic
        fun provideMatchingViewModel(
            map: @JvmSuppressWildcards MutableMap<Class<out ViewModel>, ViewModel>,
            interactor: MatchingInteractor
        ): ViewModel = MatchingViewModel(interactor).also {
            map[MatchingViewModel::class.java] = it
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideDummy(viewModel: ViewModel) = EagerTrigger()
    }
}

class EagerTrigger