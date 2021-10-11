package com.chertilov.core_api.base

sealed class Response<out T> {
    data class Success<out T>(val value: T) : Response<T>()

    data class Failure(val isNetworkError: Boolean, val errorCode: Int = 0, val message: String = "") : Response<Nothing>()

    object Loading : Response<Nothing>()
}