package com.chertilov.need4breed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chertilov.need4breed.dogs.DogsActivity
import com.chertilov.need4breed.utils.start

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        start<DogsActivity>()
        finish()
    }
}