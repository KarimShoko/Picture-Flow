package com.example.pix.data.flickr.dto

import com.google.gson.annotations.SerializedName

data class PhotoDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("secret")
    val secret: String,
    @SerializedName("server")
    val server: String,
    @SerializedName("title")
    val title: String
)