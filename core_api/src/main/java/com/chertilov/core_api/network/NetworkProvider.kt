package com.chertilov.core_api.network

interface NetworkProvider {

    fun provideDogsRemoteSource(): DogsNetwork
}