package com.example.mywallpaperapp.recyclerView

import android.view.View
import com.example.mywallpaperapp.model.Wallpaper

interface WallInteractionListener {
    fun onClickItem(data: Wallpaper, view: View)
}