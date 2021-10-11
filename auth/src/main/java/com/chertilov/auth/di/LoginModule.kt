package com.chertilov.auth.di

import androidx.lifecycle.ViewModel
import com.chertilov.auth.LoginInteractor
import com.chertilov.auth.LoginViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
abstract class LoginModule {

    @Module
    companion object {

        @Provides
        @Singleton
        @JvmStatic
        fun providePhoneNumberViewModel(
            map: @JvmSuppressWildcards MutableMap<Class<out ViewModel>, ViewModel>,
            interactor: LoginInteractor
        ): ViewModel = LoginViewModel(interactor).also {
            map[LoginViewModel::class.java] = it
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideDummy(viewModel: ViewModel) = EagerTrigger()
    }
}

class EagerTrigger