package com.chertilov.core_api.network

import com.chertilov.core_api.dto.Dog

interface DogsNetwork {

    suspend fun requestDogs(): List<Dog>
}