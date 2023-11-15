package com.example.mywallpaperapp.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApi {
    private const val BASE_URL ="https://wool-api.herokuapp.com/wallpapers/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService:RetrofitServices = retrofit.create(
        RetrofitServices::class.java
    )
}