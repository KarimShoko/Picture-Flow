package com.example.pix.domain.entity

import androidx.lifecycle.LiveData

interface FlickrRepository {
   suspend fun loadData(): Result<List<Picture>>
   fun getPictureList(): LiveData<List<Picture>>

}