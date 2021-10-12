package com.chertilov.core_api.database

interface DatabaseProvider {

    fun provideDogsDatabase(): DogsStorage

    fun provideUsersDatabase(): UsersStorage
}