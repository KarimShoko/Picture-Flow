package com.example.pix.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.pix.R
import com.example.pix.databinding.ActivityDetailPictureBinding
import com.example.pix.databinding.MainActivityBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPictureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPictureBinding
    private val viewModel: DetailPictureViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPictureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pictureUrl = intent.getStringExtra(EXTRA_PICTURE_URL)

        pictureUrl?.let {
            viewModel.getMaxResolutionUrl(it)
        }

        viewModel.maxResolutionUrl.observe(this) {
            Log.d("TEST", it)
            Picasso.get().load(it).into(binding.ivDetailPicture)
        }
    }

    companion object {
        private const val EXTRA_PICTURE_URL = "extra_picture_url"

        fun newIntent(context: Context, pictureUrl: String): Intent {
            val intent = Intent(context, DetailPictureActivity::class.java)
            intent.putExtra(EXTRA_PICTURE_URL, pictureUrl)
            return intent
        }
    }
}