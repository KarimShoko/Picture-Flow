package com.example.pix.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.pix.R
import com.example.pix.databinding.ActivityDetailPictureBinding
import com.example.pix.databinding.MainActivityBinding
import com.squareup.picasso.Picasso

class DetailPictureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPictureBinding
    private lateinit var viewModel: DetailPictureViewModel
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailPictureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(DetailPictureViewModel::class.java)


        val pictureUrl = intent.getStringExtra(EXTRA_PICTURE_URL)

        pictureUrl?.let {
            viewModel.getMaxResolutionUrl(it)
        }


        viewModel.maxResolutionUrl.observe(this) { url ->
            Picasso.get().load(url).into(binding.ivDetailPicture)
        }


        scaleGestureDetector = ScaleGestureDetector(this, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                scaleFactor *= detector.scaleFactor
                scaleFactor = scaleFactor.coerceIn(0.1f, 10.0f)
                binding.ivDetailPicture.scaleX = scaleFactor
                binding.ivDetailPicture.scaleY = scaleFactor
                return true
            }
        })
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
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