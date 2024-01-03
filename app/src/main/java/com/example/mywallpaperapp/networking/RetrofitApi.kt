package com.example.mywallpaperapp.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApi {
    private const val BASE_URL = "https://api.unsplash.com/"

    val logging = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BASIC
    )
    private var okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->

        val url = chain.request().url.newBuilder().addQueryParameter(
            "client_id", "3WX4i9EAT96ZcI69nUYDeke9YH9LCwH1LtNZV3tTpgw"
        ).build()

        val request = chain.request().newBuilder().url(url).build()

        chain.proceed(request)

    }.addInterceptor(logging).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val apiService: RetrofitServices = retrofit.create(
        RetrofitServices::class.java
    )
}