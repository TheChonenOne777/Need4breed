package com.chertilov.dogs.di

import com.chertilov.core_api.mediators.ProvidersFacade
import com.chertilov.dogs.DogDetailsFragment
import com.chertilov.dogs.DogsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DogsModule::class],
    dependencies = [ProvidersFacade::class]
)
interface DogsComponent {

    companion object {
        fun create(providersFacade: ProvidersFacade): DogsComponent =
            DaggerDogsComponent.builder()
                .providersFacade(providersFacade)
                .build()
    }

    fun inject(dogsFragment: DogsFragment)
    fun inject(dogDetailsFragment: DogDetailsFragment)
}