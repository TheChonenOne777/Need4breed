package com.chertilov.core_api.mediators

import android.content.Context
import android.content.SharedPreferences

interface AppProvider {

    fun provideContext(): Context

    fun provideSharedPreferences(): SharedPreferences
}