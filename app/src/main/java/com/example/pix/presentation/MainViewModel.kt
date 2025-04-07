package com.example.pix.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pix.domain.entity.usecases.LoadDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val loadDataUseCase: LoadDataUseCase) :
    ViewModel() {

    val state: StateFlow<PictureState> = loadDataUseCase()
        .map {
            PictureState.Result(it) as PictureState
        }
        .catch {
            emit(PictureState.Error( it.toString()))
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            PictureState.Progress
        )
}