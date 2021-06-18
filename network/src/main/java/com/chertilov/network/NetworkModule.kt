package com.chertilov.network

import com.chertilov.core_api.network.DogsNetwork
import com.chertilov.network.dogs.DogsApi
import com.chertilov.network.dogs.DogsNetworkImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class NetworkModule {

    companion object {

        private val BASE_URL = "https://dog.ceo"

        @Provides
        @Singleton
        fun provideRetorfit(okHttpClient: OkHttpClient) = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        @Singleton
        @Provides
        fun provideOkHttpClient(): OkHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        @Provides
        @Reusable
        fun provideDogsApi(retrofit: Retrofit): DogsApi = retrofit.create(DogsApi::class.java)
    }

    @Binds
    @Reusable
    abstract fun provideDogsNetwork(network: DogsNetworkImpl): DogsNetwork
}