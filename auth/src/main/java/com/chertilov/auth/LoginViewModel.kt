package com.chertilov.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chertilov.core_api.base.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val interactor: LoginInteractor) : ViewModel() {

    private val _loginResult = MutableLiveData<Response<String>>()
    val loginResult: LiveData<Response<String>> = _loginResult

    private val _timeLeft = MutableLiveData<Int>()
    val timeLeft: LiveData<Int> = _timeLeft

    var phoneNumber = " ..."
        private set

    fun onPhoneNumberEnter() {
        _loginResult.value = Response.Loading
        viewModelScope.launch {
            interactor.sendPhoneNumber(phoneNumber).collect { _loginResult.value = it }
        }
    }

    fun savePhoneNumber(number: String) {
        phoneNumber = number
    }

    fun onCodeEnter(code: String) {
        _loginResult.value = Response.Loading
        viewModelScope.launch {
            _loginResult.value = interactor.sendCode(phoneNumber, code).first()
        }
    }

    fun onResendCode() {
        startTimer(RESEND_SMS_SECONDS)
        onPhoneNumberEnter()
    }

    private fun startTimer(initialSeconds: Int) {
        viewModelScope.launch {
            (0..initialSeconds)
                .asFlow()
                .map { initialSeconds - it }
                .onEach { delay(1000) }
                .collect { _timeLeft.value = it }
        }
    }

    companion object {
        private const val RESEND_SMS_SECONDS = 120
    }
}