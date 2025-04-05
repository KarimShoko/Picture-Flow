package com.example.pix.domain.entity

import javax.inject.Inject

class LoadDataUseCase @Inject constructor (
    private val repository: FlickrRepository
) {
    suspend operator fun invoke(): Result<List<Picture>> {
        return repository.loadData()
    }
}