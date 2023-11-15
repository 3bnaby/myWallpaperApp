package com.example.mywallpaperapp.ui.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mywallpaperapp.databinding.FragmentRandomBinding
import com.example.mywallpaperapp.model.Data
import com.example.mywallpaperapp.recyclerView.RecyclerViewAdapter
import com.example.mywallpaperapp.recyclerView.WallInteractionListener
import com.example.mywallpaperapp.ui.fragments.base.BaseFragment
import com.example.mywallpaperapp.utils.Constants
import com.example.mywallpaperapp.viewModels.RandomViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RandomFragment : BaseFragment<FragmentRandomBinding>(
    FragmentRandomBinding::inflate
),WallInteractionListener {
    private val viewModel : RandomViewModel by viewModels()
    override var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(this)

    override fun initViewModel() {
        lifecycleScope.launch (Dispatchers.IO) {
            viewModel.randomPage.collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }

    override fun initRecyclerView() {
        val layoutManager = GridLayoutManager(context,3)
        binding.randomRecyclerView.layoutManager = layoutManager
        binding.randomRecyclerView.adapter = recyclerViewAdapter
    }

    override fun onClickItem(data: Data, view: View) {
        val imageData = arrayOf(data.fullImageUrl.toString(), data.blurHash.toString())
        Navigation.findNavController(view)
            .navigate(MainFragmentDirections.actionMainFragmentToDownloadFragment(
                imageData
            ))
    }

}
