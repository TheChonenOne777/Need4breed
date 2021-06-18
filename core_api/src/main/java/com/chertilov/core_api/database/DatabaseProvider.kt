package com.chertilov.core_api.database

interface DatabaseProvider {

    fun provideDatabase(): DogsStorage
}