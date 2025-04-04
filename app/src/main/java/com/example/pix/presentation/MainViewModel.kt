package com.example.pix.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pix.data.flickr.FlickrRepositoryImpl
import com.example.pix.domain.entity.GetPictureListUseCase
import com.example.pix.domain.entity.LoadDataUseCase
import com.example.pix.domain.entity.Picture
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val repository = FlickrRepositoryImpl(application)
    val loadDataUseCase = LoadDataUseCase(repository)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _pictures = MutableLiveData<List<Picture>>()
    val pictures: LiveData<List<Picture>> = _pictures

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        viewModelScope.launch {
            _isLoading.value = true
            val result = loadDataUseCase()
            result
                .onSuccess { data ->
                    _pictures.value = data
                    Log.d("MainViewModel", "Данных загружено: ${data.size}")
                }
                .onFailure { throwable ->
                    _error.value = throwable.message ?: "Unknown error"
                    Log.e("MainViewModel", "Ошибка при загрузке: ${throwable.message}")
                }
            _isLoading.value = false  // Скрываем прогресс
        }
    }
}