package com.chertilov.matching.di

import com.chertilov.core_api.mediators.ProvidersFacade
import com.chertilov.matching.MatchDialogFragment
import com.chertilov.matching.MatchingCardsFragment
import com.chertilov.matching.MatchingFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [MatchingModule::class],
    dependencies = [ProvidersFacade::class]
)
interface MatchingComponent {

    companion object {
        fun create(providersFacade: ProvidersFacade): MatchingComponent =
            DaggerMatchingComponent.builder()
                .providersFacade(providersFacade)
                .build()
    }

    fun inject(matchingFragment: MatchingFragment)
    fun inject(matchingCardsFragment: MatchingCardsFragment)
    fun inject(matchDialog: MatchDialogFragment)
}