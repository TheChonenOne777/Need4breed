package com.chertilov.navigation.mediators

import androidx.navigation.NavController
import com.chertilov.core_api.mediators.LoginMediator
import com.chertilov.core_api.mediators.ProfileMediator
import com.chertilov.navigation.NavFlow
import com.chertilov.navigation.Navigator
import javax.inject.Inject

class ProfileMediatorImpl @Inject constructor(private val navigator: Navigator) : ProfileMediator {

    override fun openProfile(navController: NavController) {
        navigator.navigate(navController, NavFlow.ProfileFlow)
    }
}