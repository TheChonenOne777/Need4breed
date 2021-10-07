package com.chertilov.core_api.mediators

import androidx.navigation.NavController

interface DogsMediator {

    fun openDogsFlow(navController: NavController)
}