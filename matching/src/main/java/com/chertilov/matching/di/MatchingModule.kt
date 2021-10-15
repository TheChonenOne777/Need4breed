package com.chertilov.matching.di

import androidx.lifecycle.ViewModelProvider
import com.chertilov.core_api.base.ViewModelFactory
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
        fun bindsFactory(interactor: MatchingInteractor): ViewModelProvider.Factory {
            return ViewModelFactory(
                mutableMapOf(
                    MatchingViewModel::class.java to MatchingViewModel(interactor)
                )
            )
        }
    }
}
