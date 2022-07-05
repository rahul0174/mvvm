package com.example.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {
    private const val BASE_URL = "https://34.204.201.39:8443/"
    fun getInstance():Retrofit{
        val httpClient = OkHttpClient.Builder().hostnameVerifier { hostname, session -> true }
        httpClient.addInterceptor { chain ->
            val originalRequest = chain.request()
            val request = originalRequest.newBuilder()
                .addHeader("lang", "eg")
                .method(originalRequest.method(), originalRequest.body())
                .build()
            chain.proceed(request)
        }
        httpClient.connectTimeout(50, TimeUnit.SECONDS)
            .readTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .build()
        val client = httpClient.build()
        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }
}