package com.example.mywallpaperapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mywallpaperapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}