package com.chertilov.navigation


sealed class NavFlow {
    object DogsFlow : NavFlow()
    object LoginFlow : NavFlow()
    object ProfileFlow : NavFlow()
    object MatchingFlow : NavFlow()
}