package com.chertilov.core

import com.chertilov.core_api.database.DatabaseProvider
import com.chertilov.core_api.mediators.AppProvider
import com.chertilov.core_api.network.NetworkProvider
import com.chertilov.core_api.viewmodel.ViewModelsProvider
import com.chertilov.core_impl.DaggerViewModelComponent
import com.chertilov.database.DaggerDatabaseComponent
import com.chertilov.network.DaggerNetworkComponent

object CoreProvidersFactory {

    fun createDatabaseBuilder(appProvider: AppProvider): DatabaseProvider =
        DaggerDatabaseComponent.builder().appProvider(appProvider).build()

    fun createNetworkBuilder(): NetworkProvider = DaggerNetworkComponent.builder().build()

    fun createViewModelBuilder(): ViewModelsProvider {
        return DaggerViewModelComponent.create()
    }
}