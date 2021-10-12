package com.chertilov.need4breed

import com.chertilov.core_api.mediators.DogsMediator
import com.chertilov.core_api.mediators.LoginMediator
import com.chertilov.core_api.mediators.MainMediator
import com.chertilov.core_api.mediators.ProfileMediator
import com.chertilov.navigation.main.MainMediatorImpl
import com.chertilov.navigation.mediators.DogsMediatorImpl
import com.chertilov.navigation.mediators.LoginMediatorImpl
import com.chertilov.navigation.mediators.ProfileMediatorImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface MediatorBindings {

    @Binds
    @Reusable
    fun bindsMainMediator(mainMediatorImpl: MainMediatorImpl): MainMediator

    @Binds
    @Reusable
    fun bindsDogsMediator(dogsMediatorImpl: DogsMediatorImpl): DogsMediator

    @Binds
    @Reusable
    fun bindsLoginMediator(loginMediatorImpl: LoginMediatorImpl): LoginMediator

    @Binds
    @Reusable
    fun bindsProfileMediator(profileMediatorImpl: ProfileMediatorImpl): ProfileMediator
}