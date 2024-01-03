package com.example.mywallpaperapp.ui.fragments.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mywallpaperapp.databinding.FragmentHomeBinding
import com.example.mywallpaperapp.model.Wallpaper
import com.example.mywallpaperapp.paging.loadingState.LoaderStateAdapter
import com.example.mywallpaperapp.recyclerView.RecyclerViewAdapter
import com.example.mywallpaperapp.recyclerView.WallInteractionListener
import com.example.mywallpaperapp.ui.fragments.MainFragmentDirections

abstract class BaseFragment : Fragment() , WallInteractionListener{

    private lateinit var binding : FragmentHomeBinding
    abstract var recyclerViewAdapter:RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)

        initViewModel()
        recyclerView()
        return binding.root
    }
    abstract fun initViewModel()

    private fun recyclerView(){
        val layoutManager = GridLayoutManager(context,3)
        binding.homeRecyclerView.layoutManager = layoutManager
        binding.homeRecyclerView.adapter = recyclerViewAdapter.withLoadStateHeaderAndFooter(
            header = LoaderStateAdapter{recyclerViewAdapter.retry()},
            footer = LoaderStateAdapter{recyclerViewAdapter.retry()}
        )

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

    private fun handleError (loadState : CombinedLoadStates){
        val errorState = loadState.source.append as? LoadState.Error
            ?: loadState.source.prepend as? LoadState.Error

        errorState?.let {
            Toast.makeText(context , "try again later" , Toast.LENGTH_LONG).show()
        }
    }
    override fun onClickItem(wallpaper: Wallpaper, view: View) {
        val imageData=arrayOf(wallpaper.photoUrls.regular.toString(), wallpaper.blurHash.toString())
        Navigation.findNavController(view)
            .navigate(
                MainFragmentDirections.actionMainFragmentToDownloadFragment(
                imageData
            ))
    }
}