package com.example.pix.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pix.R
import com.example.pix.data.flickr.FlickrRepositoryImpl
import com.example.pix.databinding.MainActivityBinding
import com.example.pix.domain.entity.LoadDataUseCase
import com.example.pix.presentation.adapters.PictureListAdapter

class MainActivity : ComponentActivity() {

    private lateinit var binding: MainActivityBinding
    private lateinit var pictureAdapter: PictureListAdapter
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setupRecyclerView()
        viewModel.pictureList.observe(this) {
            pictureAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        pictureAdapter = PictureListAdapter()
        binding.rvPicture.adapter = pictureAdapter
        binding.rvPicture.layoutManager = GridLayoutManager(this, 1)
    }


}
