package com.chertilov.network.dogs

import retrofit2.http.GET

interface DogsApi {

    @GET("/api/breed/hound/images")
    suspend fun requestDogs(): DogsResponse
}