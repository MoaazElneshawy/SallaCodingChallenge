package com.moaazelneshawy.sallacodechallenge.data.network

//import androidx.viewbinding.BuildConfig.DEBUG
import com.moaazelneshawy.sallacodechallenge.BuildConfig.DEBUG
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
Created by Moaaz Elneshawy
on 01,August,2021
 **/


object RestClient {

    private const val BASE_URL = "https://salla.sa/api/v1/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
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

    internal fun headerInterceptor(): Interceptor {
        return Interceptor {
            val original = it.request()

            val request = original.newBuilder()
                .header("Accept", "application/json")
                .header("Platform", "android")
                .header("Currency","SAR")
                .header("AppVersion","3.0.0")
                .header("Store-Identifier","1328842359")
                .build()

            it.proceed(request)
        }
    }

    internal fun loggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }
}