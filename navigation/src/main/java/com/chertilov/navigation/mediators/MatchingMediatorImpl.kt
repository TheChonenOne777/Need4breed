package com.chertilov.navigation.mediators

import androidx.navigation.NavController
import com.chertilov.core_api.mediators.MatchingMediator
import com.chertilov.navigation.NavFlow
import com.chertilov.navigation.Navigator
import javax.inject.Inject

class MatchingMediatorImpl @Inject constructor(private val navigator: Navigator) : MatchingMediator {

    override fun openMatchingFlow(navController: NavController) {
        navigator.navigate(navController, NavFlow.MatchingFlow)
    }
}