package com.chertilov.core_api.dto

data class User(
    val phoneNumber: String,
    val image: String,
    val nickname: String,
    val description: String,
    val breed: String
){
    constructor(phoneNumber: String) : this(phoneNumber, "", "", "", "")
}