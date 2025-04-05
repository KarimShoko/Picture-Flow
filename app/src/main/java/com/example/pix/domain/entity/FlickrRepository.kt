package com.example.pix.domain.entity

interface FlickrRepository {
    suspend fun loadData(): Result<List<Picture>>

}