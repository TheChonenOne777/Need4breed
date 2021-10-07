package com.chertilov.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chertilov.core_api.mediators.AppWithFacade
import com.chertilov.core_api.mediators.DogsMediator
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var dogsMediator: DogsMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SplashComponent.create((application as AppWithFacade).getFacade()).inject(this)
        dogsMediator.openDogsActivity(this)
        finish()
    }
}