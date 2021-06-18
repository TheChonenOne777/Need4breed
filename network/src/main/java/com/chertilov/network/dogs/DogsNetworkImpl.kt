package com.chertilov.network.dogs

import com.chertilov.core_api.dto.Dog
import com.chertilov.core_api.network.DogsNetwork
import javax.inject.Inject

class DogsNetworkImpl @Inject constructor(private val api: DogsApi) : DogsNetwork {

    override suspend fun requestDogs(): List<Dog> =
        api.requestDogs().message.map { Dog(image = it) }
}