package com.chertilov.core_api.mediators

interface MediatorProvider : MainMediator, DogsMediator, LoginMediator, ProfileMediator {

    fun provideMainMediator(): MainMediator

    fun provideDogsMediator(): DogsMediator

    fun provideLoginMediator(): LoginMediator

    fun provideProfileMediator(): ProfileMediator

    fun provideMatchingMediator(): MatchingMediator
}