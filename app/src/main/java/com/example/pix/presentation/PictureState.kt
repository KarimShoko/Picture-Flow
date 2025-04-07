package com.example.pix.presentation

import com.example.pix.domain.entity.Picture

sealed class PictureState {

    data class Error(val message: String) : PictureState()
    object Progress : PictureState()
    data class Result(val cryptoInfo: List<Picture>) : PictureState()
}