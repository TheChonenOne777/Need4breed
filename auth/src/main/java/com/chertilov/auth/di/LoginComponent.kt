package com.chertilov.auth.di

import com.chertilov.auth.CodeFragment
import com.chertilov.auth.PhoneNumberFragment
import com.chertilov.core.CoreProvidersFactory
import com.chertilov.core_api.mediators.ProvidersFacade
import com.chertilov.core_api.viewmodel.ViewModelsProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [LoginModule::class],
    dependencies = [ProvidersFacade::class, ViewModelsProvider::class]
)
interface LoginComponent : ViewModelsProvider {

    companion object {
        fun create(providersFacade: ProvidersFacade): LoginComponent =
            DaggerLoginComponent.builder()
                .viewModelsProvider(CoreProvidersFactory.createViewModelBuilder())
                .providersFacade(providersFacade)
                .build()
    }

    fun inject(loginFragment: PhoneNumberFragment)
    fun inject(codeFragment: CodeFragment)
}