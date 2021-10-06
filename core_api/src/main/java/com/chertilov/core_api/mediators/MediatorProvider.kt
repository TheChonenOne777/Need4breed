package com.chertilov.core_api.mediators

interface MediatorProvider : MainMediator, DogsMediator {

    fun provideMainMediator(): MainMediator

    fun provideDogsMediator(): DogsMediator
}