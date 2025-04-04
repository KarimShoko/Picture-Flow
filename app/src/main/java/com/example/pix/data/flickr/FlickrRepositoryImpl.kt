package com.example.pix.data.flickr

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.pix.data.flickr.mapper.toDb
import com.example.pix.data.flickr.mapper.toEntity
import com.example.pix.data.room.PictureDatabase
import com.example.pix.domain.entity.FlickrRepository
import com.example.pix.domain.entity.Picture

class FlickrRepositoryImpl(
    private val application: Application
) : FlickrRepository {
    private val apiService = ApiFactory.apiService
    private val pictureDao = PictureDatabase.getInstance(application).pictureDao()

    override suspend fun loadData(): Result<List<Picture>> = runCatching {
        val result = apiService.search()
        Log.d("TEST", "Результат загрузки: ${result.toString()}")
        val pictures = result.photos?.photo?.map { it.toEntity("q") } ?: emptyList()
        val picturesDbo = result.photos?.photo?.map { it.toDb("q") } ?: emptyList()
        pictureDao.insertAll(picturesDbo)
        pictures
    }

    override suspend fun loadDetailPicture(): Picture {
        TODO("Not yet implemented")
    }

    override fun getPictureList(): LiveData<List<Picture>> {
        return pictureDao.getAll().map { it.map { it.toEntity() } }
    }
}