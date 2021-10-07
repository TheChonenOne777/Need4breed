package com.chertilov.navigation.main

import android.content.Context
import android.content.Intent
import com.chertilov.core_api.mediators.MainMediator
import javax.inject.Inject

class MainMediatorImpl @Inject constructor() : MainMediator {

    override fun openMainActivity(context: Context) {
        context.startActivity(Intent(context, MainActivity::class.java))
    }
}