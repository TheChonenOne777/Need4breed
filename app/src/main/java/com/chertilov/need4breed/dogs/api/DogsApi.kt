package com.chertilov.need4breed.dogs.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DogsApi {

    @GET("/api/breed/hound/images")
    suspend fun requestDogs(): DogsResponse

    companion object {
        private val BASE_URL = "https://dog.ceo"
        private var instance: DogsApi? = null

        private val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        fun getInstance(): DogsApi = instance ?: Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogsApi::class.java)
            .also { instance = it }

    }
}