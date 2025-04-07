package com.example.pix.domain.entity

import kotlinx.coroutines.flow.Flow

interface FlickrRepository {
     fun loadData(): Flow<List<Picture>>
}