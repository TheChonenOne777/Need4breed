package com.chertilov.auth

import com.chertilov.base_auth.SessionPreferences
import com.chertilov.core_api.base.Response
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginInteractor @Inject constructor(private val sessionPreferences: SessionPreferences) {

    suspend fun sendPhoneNumber(phoneNumber: String) = flow {
        delay(1000)
        emit(Response.Success(""))
    }

    suspend fun sendCode(code: String) = flow {
        delay(1000)
        emit(Response.Success("123").apply { sessionPreferences.logIn(value) })
    }
}