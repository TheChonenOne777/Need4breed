package com.chertilov.navigation

import android.app.Activity
import androidx.navigation.findNavController
import javax.inject.Inject

class Navigator @Inject constructor() {

    fun navigate(activity: Activity, flow: NavFlow) = when (flow) {
        NavFlow.DogsFlow -> activity.findNavController(R.id.nav_host_fragment)
            .navigate(R.id.action_global_dogs_flow)
    }
}