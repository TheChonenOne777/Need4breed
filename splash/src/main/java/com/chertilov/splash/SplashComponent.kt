package com.chertilov.splash

import com.chertilov.core_api.mediators.ProvidersFacade
import dagger.Component


@Component(
    dependencies = [ProvidersFacade::class]
)
interface SplashComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): SplashComponent {
            return DaggerSplashComponent.builder().providersFacade(providersFacade).build()
        }
    }

    fun inject(splashActivity: SplashActivity)
}