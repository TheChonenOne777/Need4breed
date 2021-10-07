package com.chertilov.navigation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chertilov.core_api.mediators.AppWithFacade
import com.chertilov.navigation.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainComponent.create((application as AppWithFacade).getFacade()).inject(this)
        setContentView(R.layout.activity_main)
    }
}