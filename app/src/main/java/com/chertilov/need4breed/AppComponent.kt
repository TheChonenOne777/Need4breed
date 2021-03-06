package com.chertilov.need4breed

import android.app.Application
import android.content.Context
import com.chertilov.core_api.mediators.AppProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AppProvider {

    companion object {

        private var appComponent: AppProvider? = null

        fun create(application: Application): AppProvider =
            appComponent ?: DaggerAppComponent.builder()
                .application(application.applicationContext)
                .build()
                .also { appComponent = it }
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(context: Context): Builder

        fun build(): AppComponent
    }
}