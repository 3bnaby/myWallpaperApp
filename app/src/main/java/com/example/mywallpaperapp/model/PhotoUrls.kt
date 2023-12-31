package com.example.mywallpaperapp.model

import com.google.gson.annotations.SerializedName

data class PhotoUrls(

    @SerializedName("raw") var raw: String,
    @SerializedName("full") var full: String,
    @SerializedName("regular") var regular: String,
    @SerializedName("small") var small: String,
    @SerializedName("thumb") var thumb: String

)
