package com.example.mywallpaperapp.model


import com.google.gson.annotations.SerializedName

data class Paggination(
    @SerializedName("next")
    val next: Next?
)