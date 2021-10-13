package com.chertilov.matching.di

import com.chertilov.core.CoreProvidersFactory
import com.chertilov.core_api.mediators.ProvidersFacade
import com.chertilov.core_api.viewmodel.ViewModelsProvider
import com.chertilov.matching.MatchDialogFragment
import com.chertilov.matching.MatchingFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [MatchingModule::class],
    dependencies = [ProvidersFacade::class, ViewModelsProvider::class]
)
interface MatchingComponent : ViewModelsProvider {

    companion object {
        fun create(providersFacade: ProvidersFacade): MatchingComponent =
            DaggerMatchingComponent.builder()
                .viewModelsProvider(CoreProvidersFactory.createViewModelBuilder())
                .providersFacade(providersFacade)
                .build()
    }

    fun inject(matchingFragment: MatchingFragment)
    fun inject(matchDialog: MatchDialogFragment)
}