package com.example.mywallpaperapp.ui.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mywallpaperapp.recyclerView.RecyclerViewAdapter
import com.example.mywallpaperapp.ui.fragments.base.BaseFragment
import com.example.mywallpaperapp.viewModels.PopularViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PopularFragment: BaseFragment(){
    private val viewModel : PopularViewModel by viewModels()
    override var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(this)
    override fun initViewModel() {
        lifecycleScope.launch {
            viewModel.popularPage.collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }
}
