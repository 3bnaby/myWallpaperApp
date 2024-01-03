package com.example.mywallpaperapp.ui.fragments

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mywallpaperapp.databinding.FragmentDownloadBinding
import com.example.mywallpaperapp.utils.BlurHashDecoder

class DownloadFragment : Fragment() {
    private lateinit var binding: FragmentDownloadBinding
    private val args: DownloadFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDownloadBinding.inflate(inflater)
        loadImage(args.imageData[0])
        addCallBack()
        bottomSheet()
        return binding.root
    }

    private fun loadImage(url: String) {
        val blurHash = BlurHashDecoder.decode(args.imageData[1])
        Glide.with(this)
            .load(url)
            .centerCrop()
            .placeholder(blurHash?.toDrawable(this.resources))
            .error(blurHash)
            .into(binding.downloadImageView)
        binding.ConstraintBackGround.background = BitmapDrawable(this.resources, blurHash)
    }

    private fun addCallBack() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun bottomSheet() {
        val bottomSheet = BottomSheetFragment.newInstance(args.imageData[0])
        binding.downloadButton.setOnClickListener {
            bottomSheet.show(requireActivity().supportFragmentManager, "bottomSheet")
        }
    }

}