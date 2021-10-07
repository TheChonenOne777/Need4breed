package com.chertilov.dogs.di

import com.chertilov.core.CoreProvidersFactory
import com.chertilov.core_api.mediators.ProvidersFacade
import com.chertilov.core_api.viewmodel.ViewModelsProvider
import com.chertilov.dogs.DogDetailsActivity
import com.chertilov.dogs.DogsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DogsModule::class],
    dependencies = [ProvidersFacade::class, ViewModelsProvider::class]
)
interface DogsComponent : ViewModelsProvider {

    companion object {
        fun create(providersFacade: ProvidersFacade): DogsComponent =
            DaggerDogsComponent.builder()
                .viewModelsProvider(CoreProvidersFactory.createViewModelBuilder())
                .providersFacade(providersFacade)
                .build()
    }

    fun inject(dogsActivity: DogsActivity)
    fun inject(dogDetailsActivity: DogDetailsActivity)
}