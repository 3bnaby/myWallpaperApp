package com.example.mywallpaperapp.recyclerView

import android.view.View
import com.example.mywallpaperapp.model.Data

interface WallInteractionListener {
    fun onClickItem(data: Data, view: View)
}