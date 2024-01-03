package com.example.mywallpaperapp.networking

import com.example.mywallpaperapp.model.Wallpaper
import com.example.mywallpaperapp.networking.response.WallpaperResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {
    @GET(ENDPOINT_PHOTOS)
    suspend fun getLatestFromApi(

        @Query(PARAMS_PAGE) page: Int = 0,
        @Query(PARAMS_PER_PAGE) perPage: Int = 25,
        @Query(PARAMS_ORDER_BY) orderBy: String = PARAMS_LATEST,
    ): List<Wallpaper>


    @GET(ENDPOINT_PHOTOS)
    suspend fun getPopularFromApi(
        @Query(PARAMS_PAGE) page: Int = 0,
        @Query(PARAMS_PER_PAGE) perPage: Int = 25,
        @Query(PARAMS_ORDER_BY) orderBy: String = PARAMS_POPULAR,
    ): List<Wallpaper>

    @GET("category")
    suspend fun getCategoryFromApi(
        @Query("page") page: Int?,
        @Query("category") category: String
    ): Wallpaper
}