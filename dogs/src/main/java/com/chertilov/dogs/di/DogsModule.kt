package com.chertilov.dogs.di

import com.chertilov.dogs.DogsRepository
import com.chertilov.dogs.DogsRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DogsModule{

    @Binds
    @Singleton
    abstract fun bindDogsRepository(repo: DogsRepositoryImpl): DogsRepository
}