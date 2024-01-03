package com.example.mywallpaperapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mywallpaperapp.model.Wallpaper
import com.example.mywallpaperapp.networking.RetrofitServices

class PopularPagingSource(private val apiService : RetrofitServices):PagingSource<Int,Wallpaper>() {
    override fun getRefreshKey(state: PagingState<Int, Wallpaper>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1)?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Wallpaper> {
        return try {
            val nextPage = params.key?: FIRST_PAGE_INDEX
            val responsePopular = apiService.getPopularFromApi(nextPage)

            LoadResult.Page(
                data = responsePopular,
                prevKey = null,
                nextKey = nextPage.plus(1),
            )
        } catch (e:Exception){
            LoadResult.Error(e)
        }
    }
    companion object{
        private const val FIRST_PAGE_INDEX = 1
    }
}