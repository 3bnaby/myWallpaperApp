package com.example.mywallpaperapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mywallpaperapp.databinding.FragmentCategoriesBinding
import com.example.mywallpaperapp.recyclerView.CategoryAdapter
import com.example.mywallpaperapp.utils.APICategory


class CategoriesFragment :Fragment(){
    private lateinit var binding : FragmentCategoriesBinding
    private lateinit var recyclerViewAdapter : CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentCategoriesBinding.inflate(inflater , container , false)
        initRecyclerViewAdapter()
        return binding.root
    }

    private fun initRecyclerViewAdapter(){
        val layoutManager = GridLayoutManager(context , 2)
        recyclerViewAdapter = CategoryAdapter(APICategory.list)
        binding.categoryRecyclerView.layoutManager = layoutManager
        binding.categoryRecyclerView.adapter = recyclerViewAdapter
    }
}
