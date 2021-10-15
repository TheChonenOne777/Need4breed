package com.chertilov.matching

import com.chertilov.core_api.dto.User
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class StateHelper @Inject constructor() {

    var matchedUser: User? = null
}