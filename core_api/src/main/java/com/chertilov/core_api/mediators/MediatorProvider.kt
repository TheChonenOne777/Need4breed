package com.chertilov.core_api.mediators

interface MediatorProvider : MainMediator, DogsMediator, LoginMediator {

    fun provideMainMediator(): MainMediator

    fun provideDogsMediator(): DogsMediator

    fun provideLoginMediator(): LoginMediator
}