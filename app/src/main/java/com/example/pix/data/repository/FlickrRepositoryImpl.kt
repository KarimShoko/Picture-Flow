package com.example.pix.data.repository

import com.example.pix.data.flickr.ApiService
import com.example.pix.data.flickr.mapper.toEntity
import com.example.pix.domain.entity.FlickrRepository
import com.example.pix.domain.entity.Picture
import javax.inject.Inject

class FlickrRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : FlickrRepository {

    override suspend fun loadData(): Result<List<Picture>> = runCatching {
        val result = apiService.search()
        val pictures = result.photos?.photo?.map { it.toEntity("q") } ?: emptyList()
        pictures
    }
}