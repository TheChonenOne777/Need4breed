package com.chertilov.database

import com.chertilov.core_api.database.DatabaseProvider
import com.chertilov.core_api.mediators.AppProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppProvider::class],
    modules = [DatabaseModule::class]
)
interface DatabaseComponent : DatabaseProvider