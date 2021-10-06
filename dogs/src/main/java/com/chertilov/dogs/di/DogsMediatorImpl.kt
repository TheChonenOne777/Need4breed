package com.chertilov.dogs.di

import android.content.Context
import android.content.Intent
import com.chertilov.core_api.mediators.DogsMediator
import com.chertilov.dogs.DogsActivity
import javax.inject.Inject

class DogsMediatorImpl @Inject constructor() : DogsMediator {

    override fun openDogsActivity(context: Context) {
        context.startActivity(Intent(context, DogsActivity::class.java))
    }
}