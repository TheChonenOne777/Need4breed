package com.chertilov.need4breed

import android.app.Application
import com.chertilov.core.CoreProvidersFactory
import com.chertilov.core_api.database.DatabaseProvider
import com.chertilov.core_api.mediators.AppProvider
import com.chertilov.core_api.mediators.ProvidersFacade
import com.chertilov.core_api.network.NetworkProvider
import dagger.Component

@Component(
    dependencies = [AppProvider::class, DatabaseProvider::class, NetworkProvider::class],
)
interface FacadeComponent : ProvidersFacade {

    companion object {

        fun init(app: Application): FacadeComponent =
            DaggerFacadeComponent.builder()
                .appProvider(AppComponent.create(app))
                .databaseProvider(CoreProvidersFactory.createDatabaseBuilder(AppComponent.create(app)))
                .networkProvider(CoreProvidersFactory.createNetworkBuilder())
                .build()
    }

    fun inject(app: App)
}