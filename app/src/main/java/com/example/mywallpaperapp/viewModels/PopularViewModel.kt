package com.example.mywallpaperapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.mywallpaperapp.paging.PopularPagingSource
import com.example.mywallpaperapp.repository.MainRepository

class PopularViewModel :ViewModel() {
   private val repository = MainRepository()
    val popularPage = Pager(config = PagingConfig(pageSize = 30), pagingSourceFactory = {
        PopularPagingSource(repository.retroService())
    })
        .flow.cachedIn(viewModelScope)
}