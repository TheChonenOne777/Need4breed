package com.chertilov.profile.di

import com.chertilov.auth.di.ProfileModule
import com.chertilov.core.CoreProvidersFactory
import com.chertilov.core_api.mediators.ProvidersFacade
import com.chertilov.core_api.viewmodel.ViewModelsProvider
import com.chertilov.profile.ProfileFragment
import com.chertilov.profile.matches.MatchesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ProfileModule::class],
    dependencies = [ProvidersFacade::class, ViewModelsProvider::class]
)
interface ProfileComponent : ViewModelsProvider {

    companion object {
        fun create(providersFacade: ProvidersFacade): ProfileComponent =
            DaggerProfileComponent.builder()
                .viewModelsProvider(CoreProvidersFactory.createViewModelBuilder())
                .providersFacade(providersFacade)
                .build()
    }

    fun inject(profileFragment: ProfileFragment)
    fun inject(matchesFragment: MatchesFragment)
}