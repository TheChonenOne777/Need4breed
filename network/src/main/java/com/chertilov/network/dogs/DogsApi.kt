package com.chertilov.network.dogs

import retrofit2.http.GET

interface DogsApi {

    @GET("/api/breed/retriever/golden/images")
    suspend fun requestDogs(): DogsResponse
}