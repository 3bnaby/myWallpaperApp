package com.example.mywallpaperapp.ui.fragments

import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mywallpaperapp.databinding.FragmentHomeBinding
import com.example.mywallpaperapp.model.Data
import com.example.mywallpaperapp.recyclerView.RecyclerViewAdapter
import com.example.mywallpaperapp.recyclerView.WallInteractionListener
import com.example.mywallpaperapp.ui.fragments.base.BaseFragment
import com.example.mywallpaperapp.viewModels.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) , WallInteractionListener {
    private val viewModel : HomeViewModel by viewModels()
    override var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter( this)

    override fun initViewModel() {
        lifecycleScope.launch {
            viewModel.homePage.collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }
    override fun initRecyclerView() {
        val layoutManager = GridLayoutManager(context,3)
        binding.homeRecyclerView.layoutManager = layoutManager
        binding.homeRecyclerView.adapter = recyclerViewAdapter

        recyclerViewAdapter.addLoadStateListener { loadState ->
            binding.homeRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
            handleError(loadState)
        }
        binding.buttonRetry.setOnClickListener {
            recyclerViewAdapter.retry()
        }
    }
    override fun onClickItem(data: Data, view: View) {
        val imageData = arrayOf(data.fullImageUrl.toString(), data.blurHash.toString())
                       Navigation.findNavController(view)
                           .navigate(MainFragmentDirections.actionMainFragmentToDownloadFragment(
                               imageData
                           ))
                   }
}
