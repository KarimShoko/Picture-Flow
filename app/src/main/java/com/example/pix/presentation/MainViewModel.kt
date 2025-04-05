package com.example.pix.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pix.data.repository.FlickrRepositoryImpl
import com.example.pix.domain.entity.LoadDataUseCase
import com.example.pix.domain.entity.Picture
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(private val loadDataUseCase: LoadDataUseCase)  : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _pictures = MutableLiveData<List<Picture>>()
    val pictures: LiveData<List<Picture>>
        get() = _pictures

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

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
                    _error.value = true
                }
            _isLoading.value = false
        }
    }
}