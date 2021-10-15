package com.chertilov.auth.di

import androidx.lifecycle.ViewModelProvider
import com.chertilov.auth.LoginInteractor
import com.chertilov.auth.LoginViewModel
import com.chertilov.core_api.base.ViewModelFactory
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
        fun bindsFactory(interactor: LoginInteractor): ViewModelProvider.Factory {
            return ViewModelFactory(
                mutableMapOf(
                    LoginViewModel::class.java to LoginViewModel(interactor)
                )
            )
        }
    }
}
