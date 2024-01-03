package com.example.mywallpaperapp.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mywallpaperapp.model.Wallpaper
import com.example.mywallpaperapp.networking.RetrofitServices

class HomePagingSource(private val apiService: RetrofitServices) : PagingSource<Int, Wallpaper>() {
    override fun getRefreshKey(state: PagingState<Int, Wallpaper>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Wallpaper> {

        var nextPage = params.key ?: FIRST_PAGE_INDEX
        Log.d("HomePagingSource", "start loading $nextPage")

        return try {
            val responseHome = apiService.getLatestFromApi(nextPage)
            Log.d("HomePagingSource", "responseHome $responseHome")
            val result = LoadResult.Page(
                data = responseHome,
                prevKey = null,
                nextKey = nextPage.plus(1)
            )
            return result

        } catch (e: Exception) {
            Log.e("HomePagingSource", e.toString())
            LoadResult.Error(e)
        }

    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}