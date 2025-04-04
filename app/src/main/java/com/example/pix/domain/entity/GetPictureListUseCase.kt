package com.example.pix.domain.entity

import androidx.lifecycle.LiveData

class GetPictureListUseCase (
    private val repository: FlickrRepository
) {
    operator fun invoke(): LiveData<List<Picture>> {
        return repository.getPictureList()
    }
}