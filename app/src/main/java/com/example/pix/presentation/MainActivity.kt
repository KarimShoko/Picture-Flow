package com.example.pix.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pix.databinding.MainActivityBinding
import com.example.pix.presentation.adapters.PictureListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var binding: MainActivityBinding
    private lateinit var pictureAdapter: PictureListAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.state.collect {
                    when (it) {
                        is PictureState.Error -> {
                            binding.progressBarLoading.visibility = View.GONE
                            binding.rvPictures.visibility = View.GONE
                            Toast.makeText(this@MainActivity, "Ошибка при загрузке данных:${it.message}", Toast.LENGTH_LONG)
                                .show()
                        }
                        PictureState.Progress -> {
                            binding.progressBarLoading.visibility = View.VISIBLE
                            binding.rvPictures.visibility = View.GONE
                        }
                        is PictureState.Result -> {
                            binding.progressBarLoading.visibility = View.GONE
                            binding.rvPictures.visibility = View.VISIBLE
                            Log.d("MainActivity", "${it.cryptoInfo}")
                            pictureAdapter.submitList(it.cryptoInfo)
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        pictureAdapter = PictureListAdapter()
        binding.rvPictures.adapter = pictureAdapter
        binding.rvPictures.layoutManager = GridLayoutManager(this, 3)
        pictureAdapter.onPictureClickListener = {
            val intent = DetailPictureActivity.newIntent(this, it.url)
            startActivity(intent)
        }
    }
}
