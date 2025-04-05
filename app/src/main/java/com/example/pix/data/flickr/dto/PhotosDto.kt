package com.example.pix.data.flickr.dto

import com.google.gson.annotations.SerializedName

data class PhotosDto(
    @SerializedName("photo")
    val photo: List<PhotoDto>,
)