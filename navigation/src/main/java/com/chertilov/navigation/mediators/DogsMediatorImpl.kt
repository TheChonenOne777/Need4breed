package com.chertilov.navigation.mediators

import android.app.Activity
import com.chertilov.core_api.mediators.DogsMediator
import com.chertilov.navigation.NavFlow
import com.chertilov.navigation.Navigator
import javax.inject.Inject

class DogsMediatorImpl @Inject constructor(private val navigator: Navigator) : DogsMediator {

    override fun openDogsFlow(activity: Activity) {
        navigator.navigate(activity, NavFlow.DogsFlow)
    }
}