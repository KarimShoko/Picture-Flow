package com.example.pix.domain.entity.usecases

import com.example.pix.domain.entity.FlickrRepository
import com.example.pix.domain.entity.Picture
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val repository: FlickrRepository
) {
    suspend operator fun invoke(): Result<List<Picture>> {
        return repository.loadData()
    }
}