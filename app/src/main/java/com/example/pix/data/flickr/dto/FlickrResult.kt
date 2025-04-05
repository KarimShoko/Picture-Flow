package com.example.pix.data.flickr.dto

import com.google.gson.annotations.SerializedName

data class FlickrResult(
    @SerializedName("photos")
    val photos: PhotosDto?,
)