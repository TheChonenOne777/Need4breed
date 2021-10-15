package com.chertilov.auth.di

import androidx.lifecycle.ViewModelProvider
import com.chertilov.core_api.base.ViewModelFactory
import com.chertilov.profile.ProfileInteractor
import com.chertilov.profile.ProfileViewModel
import com.chertilov.profile.matches.MatchesInteractor
import com.chertilov.profile.matches.MatchesViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
abstract class ProfileModule {

    @Module
    companion object {

        @Provides
        @Singleton
        @JvmStatic
        fun bindsFactory(profileInteractor: ProfileInteractor, matchesInteractor: MatchesInteractor): ViewModelProvider.Factory {
            return ViewModelFactory(
                mutableMapOf(
                    ProfileViewModel::class.java to ProfileViewModel(profileInteractor),
                    MatchesViewModel::class.java to MatchesViewModel(matchesInteractor)
                )
            )
        }
    }
}
