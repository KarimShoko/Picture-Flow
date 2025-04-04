package com.example.pix.domain.entity

class LoadDataUseCase (
    private val repository: FlickrRepository
) {
    suspend operator fun invoke(): Result<List<Picture>> {
        return repository.loadData()
    }
}