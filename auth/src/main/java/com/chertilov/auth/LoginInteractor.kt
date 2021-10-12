package com.chertilov.auth

import com.chertilov.base_auth.SessionPreferences
import com.chertilov.core_api.base.Response
import com.chertilov.core_api.database.UsersStorage
import com.chertilov.core_api.dto.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginInteractor @Inject constructor(
    private val sessionPreferences: SessionPreferences,
    private val usersStorage: UsersStorage
) {

    suspend fun sendPhoneNumber(phoneNumber: String) = flow {
        delay(500)
        emit(Response.Success(""))
    }

    suspend fun sendCode(phoneNumber: String, code: String) = flow {
        delay(500)
        emit(Response.Success("123").apply {
            sessionPreferences.logIn(value)
            usersStorage.saveUser(User(phoneNumber))
        })
    }
}