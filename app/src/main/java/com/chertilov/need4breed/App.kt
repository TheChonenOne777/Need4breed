package com.chertilov.need4breed

import android.app.Application
import com.chertilov.core_api.mediators.AppWithFacade
import com.chertilov.core_api.mediators.ProvidersFacade

class App : Application(), AppWithFacade {

    override fun onCreate() {
        super.onCreate()
        (getFacade() as FacadeComponent).inject(this)
    }

    override fun getFacade(): ProvidersFacade =
        facadeComponent ?: FacadeComponent.init(this).also { facadeComponent = it }

    companion object {
        private var facadeComponent: FacadeComponent? = null
    }
}