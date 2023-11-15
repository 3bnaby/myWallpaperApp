package com.example.mywallpaperapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mywallpaperapp.model.Data
import com.example.mywallpaperapp.networking.RetrofitServices

class RandomPagingSource(private val apiService : RetrofitServices) : PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage =state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1)?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val nextPage = params.key?: FIRST_PAGE_INDEX
            val responseRandom = apiService.getRandomFromApi(nextPage)
            LoadResult.Page(
                data = responseRandom.data,
                prevKey = null,
                nextKey = responseRandom.paggination?.next?.page)
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
    companion object{
        private const val FIRST_PAGE_INDEX = 1
    }
}