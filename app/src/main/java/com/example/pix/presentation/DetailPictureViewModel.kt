package com.example.pix.presentation

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
    class DetailPictureViewModel @Inject constructor() :ViewModel() {
        private val _maxResolutionUrl = MutableLiveData<String>()
        val maxResolutionUrl: LiveData<String> = _maxResolutionUrl

        fun getMaxResolutionUrl(url: String) {
            val newUrl = url.replace("_q.jpg", "_b.jpg")
            _maxResolutionUrl.value = newUrl
        }

    }