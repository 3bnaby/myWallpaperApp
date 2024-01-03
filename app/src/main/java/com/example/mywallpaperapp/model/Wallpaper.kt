package com.example.mywallpaperapp.model


import com.google.gson.annotations.SerializedName

data class Wallpaper(
    @SerializedName("id") var id: String,
    @SerializedName("created_at") var createdAt: String,
    @SerializedName("updated_at") var updatedAt: String,
    @SerializedName("width") var width: Int,
    @SerializedName("height") var height: Int,
    @SerializedName("color") var color: String,
    @SerializedName("blur_hash") var blurHash: String,
    @SerializedName("likes") var likes: Int,
    @SerializedName("liked_by_user") var likedByUser: Boolean,
    @SerializedName("description") var description: String,
    @SerializedName("urls") var photoUrls: PhotoUrls
)