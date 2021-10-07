package com.chertilov.navigation.mediators

import androidx.navigation.NavController
import com.chertilov.core_api.mediators.DogsMediator
import com.chertilov.navigation.NavFlow
import com.chertilov.navigation.Navigator
import com.chertilov.navigation.R
import javax.inject.Inject

class DogsMediatorImpl @Inject constructor(private val navigator: Navigator) : DogsMediator {

    override fun openDogsFlow(navController: NavController) {
        navigator.navigate(navController, NavFlow.DogsFlow)
    }
}