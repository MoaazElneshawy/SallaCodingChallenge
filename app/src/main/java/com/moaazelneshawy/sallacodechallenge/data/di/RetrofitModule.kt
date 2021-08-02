package com.moaazelneshawy.sallacodechallenge.data.di

import com.moaazelneshawy.sallacodechallenge.BuildConfig
import com.moaazelneshawy.sallacodechallenge.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
Created by Moaaz Elneshawy
on 01,August,2021
 **/

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    private val BASE_URL = "https://salla.sa/api/v1/"

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build().create(ApiService::class.java)
    }

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(headerInterceptor())
            .addInterceptor(loggingInterceptor())
            .build()
    }

    private fun headerInterceptor(): Interceptor {
        return Interceptor {
            val original = it.request()

            val request = original.newBuilder()
                .header("Accept", "application/json")
                .header("Platform", "android")
                .header("Currency", "SAR")
                .header("AppVersion", "3.0.0")
                .header("Store-Identifier", "1328842359")
                .build()

            it.proceed(request)
        }
    }

    private fun loggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

}