package com.example.pix.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pix.databinding.MainActivityBinding
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
        viewModel.pictures.observe(this) {
            pictureAdapter.submitList(it)

        }
        viewModel.isLoading.observe(this){
            if (it==true){
                binding.progressBarLoading.visibility=View.VISIBLE
            }else{
                binding.progressBarLoading.visibility=View.GONE
            }
        }
    }

    private fun setupRecyclerView() {
        pictureAdapter = PictureListAdapter()
        binding.rvPictures.adapter = pictureAdapter
        binding.rvPictures.layoutManager = GridLayoutManager(this, 3)
        pictureAdapter.onPictureClickListener = {
            val intent=DetailPictureActivity.newIntent(this,it.url)
            startActivity(intent)
        }
    }
}
