package com.chertilov.navigation

import androidx.navigation.NavController
import javax.inject.Inject

class Navigator @Inject constructor() {

    fun navigate(navController: NavController, flow: NavFlow) = when (flow) {
        NavFlow.DogsFlow -> navController.navigate(MainNavGraphDirections.actionGlobalDogsFlow())
        NavFlow.LoginFlow -> navController.navigate(MainNavGraphDirections.actionGlobalLoginFlow())
    }
}