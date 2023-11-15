package com.example.mywallpaperapp.viewModels

import android.graphics.pdf.PdfDocument.Page
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.mywallpaperapp.paging.RandomPagingSource
import com.example.mywallpaperapp.repository.MainRepository

class RandomViewModel :ViewModel() {
    private val repository = MainRepository()
    val randomPage = Pager(config = PagingConfig(pageSize = 30), pagingSourceFactory = {
        RandomPagingSource(repository.retroService())
    })
        .flow.cachedIn(viewModelScope)
}