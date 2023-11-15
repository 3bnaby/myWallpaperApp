package com.example.mywallpaperapp.ui.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mywallpaperapp.databinding.FragmentPopularBinding
import com.example.mywallpaperapp.model.Data
import com.example.mywallpaperapp.recyclerView.RecyclerViewAdapter
import com.example.mywallpaperapp.recyclerView.WallInteractionListener
import com.example.mywallpaperapp.ui.fragments.base.BaseFragment
import com.example.mywallpaperapp.utils.Constants
import com.example.mywallpaperapp.viewModels.PopularViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PopularFragment : BaseFragment<FragmentPopularBinding>(
    FragmentPopularBinding::inflate
),WallInteractionListener {
    private val viewModel : PopularViewModel by viewModels()
    override var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(this)

    override fun initViewModel() {
        lifecycleScope.launch {
            viewModel.popularPage.collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }

    override fun initRecyclerView() {
        val layoutManager = GridLayoutManager(context , 3)
        binding.popularRecyclerView.layoutManager = layoutManager
        binding.popularRecyclerView.adapter = recyclerViewAdapter
    }

    override fun onClickItem(data: Data, view: View) {
        val imageData = arrayOf(data.fullImageUrl.toString(), data.blurHash.toString())
        Navigation.findNavController(view)
            .navigate(MainFragmentDirections.actionMainFragmentToDownloadFragment(
                imageData
            ))
    }
}
