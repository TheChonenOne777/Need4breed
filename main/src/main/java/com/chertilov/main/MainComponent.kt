package com.chertilov.main

import com.chertilov.core_api.mediators.ProvidersFacade
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [ProvidersFacade::class]
)
interface MainComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): MainComponent {
            return DaggerMainComponent.builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(mainActivity: MainActivity)
    fun inject(startFragment: StartFragment)
}