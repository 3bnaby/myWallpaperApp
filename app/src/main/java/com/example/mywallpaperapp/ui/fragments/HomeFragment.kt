package com.example.mywallpaperapp.ui.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mywallpaperapp.recyclerView.RecyclerViewAdapter
import com.example.mywallpaperapp.ui.fragments.base.BaseFragment
import com.example.mywallpaperapp.viewModels.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment(){
    private val viewModel :HomeViewModel by viewModels()
    override var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(this)
    override fun initViewModel() {
        lifecycleScope.launch {
            viewModel.homePage.collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }
}
