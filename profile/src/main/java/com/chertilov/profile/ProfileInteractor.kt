package com.chertilov.profile

import com.chertilov.base_auth.SessionPreferences
import com.chertilov.core_api.database.UsersStorage
import com.chertilov.core_api.dto.User
import javax.inject.Inject

class ProfileInteractor @Inject constructor(
    private val sessionPreferences: SessionPreferences,
    private val usersStorage: UsersStorage
) {

    fun getCurrentUser() = usersStorage.getUser(sessionPreferences.getPhoneNumber())
}