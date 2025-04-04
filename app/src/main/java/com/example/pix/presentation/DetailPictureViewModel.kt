package com.example.pix.presentation

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailPictureViewModel :ViewModel() {
    private val _maxResolutionUrl = MutableLiveData<String>()
    val maxResolutionUrl: LiveData<String> = _maxResolutionUrl

    fun getMaxResolutionUrl(url: String) {
        val newUrl = url.replace("_q.jpg", "_w.jpg")
        _maxResolutionUrl.value = newUrl
    }

}