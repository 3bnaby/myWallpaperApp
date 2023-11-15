package com.example.mywallpaperapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.mywallpaperapp.paging.HomePagingSource
import com.example.mywallpaperapp.repository.MainRepository

class HomeViewModel :ViewModel(){
    private val repository = MainRepository()

    val homePage =Pager(config = PagingConfig(pageSize = 30), pagingSourceFactory = {
        HomePagingSource(repository.retroService())
    })
        .flow.cachedIn(viewModelScope)


}