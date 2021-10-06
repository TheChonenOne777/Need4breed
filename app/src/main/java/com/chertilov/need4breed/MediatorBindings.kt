package com.chertilov.need4breed

import com.chertilov.core_api.mediators.DogsMediator
import com.chertilov.core_api.mediators.MainMediator
import com.chertilov.dogs.di.DogsMediatorImpl
import com.chertilov.main.MainMediatorImpl
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
    fun bindsHomeMediator(dogsMediatorImpl: DogsMediatorImpl): DogsMediator
}