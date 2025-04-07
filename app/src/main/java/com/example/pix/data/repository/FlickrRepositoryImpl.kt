package com.example.pix.data.repository

import com.example.pix.data.flickr.ApiService
import com.example.pix.data.flickr.mapper.toEntity
import com.example.pix.domain.entity.FlickrRepository
import com.example.pix.domain.entity.Picture
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FlickrRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : FlickrRepository {

    override  fun loadData(): Flow<List<Picture>> = flow {
        while (true) {
            val result = apiService.search()
            val pictures = result.photos?.photo?.map { it.toEntity("q") } ?: emptyList()
            emit(pictures.toList())
            delay(10000)
        }
    }
}