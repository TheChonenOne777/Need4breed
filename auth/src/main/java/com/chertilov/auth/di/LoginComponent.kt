package com.chertilov.auth.di

import com.chertilov.auth.CodeFragment
import com.chertilov.auth.PhoneNumberFragment
import com.chertilov.core_api.mediators.ProvidersFacade
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [LoginModule::class],
    dependencies = [ProvidersFacade::class]
)
interface LoginComponent {

    companion object {
        fun create(providersFacade: ProvidersFacade): LoginComponent =
            DaggerLoginComponent.builder()
                .providersFacade(providersFacade)
                .build()
    }

    fun inject(loginFragment: PhoneNumberFragment)
    fun inject(codeFragment: CodeFragment)
}