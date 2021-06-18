package com.chertilov.core_api.mediators

import android.content.Context

interface AppProvider {

    fun provideContext(): Context
}