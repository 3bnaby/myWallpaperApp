package com.example.mywallpaperapp.viewModels

import androidx.lifecycle.ViewModel
import com.example.mywallpaperapp.repository.MainRepository

class CategoryViewModel :ViewModel() {
  private  val repository =MainRepository()
}