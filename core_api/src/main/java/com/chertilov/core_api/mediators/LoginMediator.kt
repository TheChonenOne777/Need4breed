package com.chertilov.core_api.mediators

import androidx.navigation.NavController

interface LoginMediator {

    fun openLoginFlow(navController: NavController)
}