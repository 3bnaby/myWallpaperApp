package com.example.mywallpaperapp.repository

import com.example.mywallpaperapp.networking.RetrofitApi
import com.example.mywallpaperapp.networking.RetrofitServices

class MainRepository {
    fun retroService():RetrofitServices = RetrofitApi.apiService
}