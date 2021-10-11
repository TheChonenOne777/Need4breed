package com.chertilov.navigation.mediators

import androidx.navigation.NavController
import com.chertilov.core_api.mediators.LoginMediator
import com.chertilov.navigation.NavFlow
import com.chertilov.navigation.Navigator
import javax.inject.Inject

class LoginMediatorImpl @Inject constructor(private val navigator: Navigator) : LoginMediator {

    override fun openLoginFlow(navController: NavController) {
        navigator.navigate(navController, NavFlow.LoginFlow)
    }
}