package com.chertilov.auth.di

import androidx.lifecycle.ViewModel
import com.chertilov.profile.ProfileInteractor
import com.chertilov.profile.ProfileViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
abstract class ProfileModule {

    @Module
    companion object {

        @Provides
        @Singleton
        @JvmStatic
        fun provideProfileViewModel(
            map: @JvmSuppressWildcards MutableMap<Class<out ViewModel>, ViewModel>,
            interactor: ProfileInteractor
        ): ViewModel = ProfileViewModel(interactor).also {
            map[ProfileViewModel::class.java] = it
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideDummy(viewModel: ViewModel) = EagerTrigger()
    }
}

class EagerTrigger