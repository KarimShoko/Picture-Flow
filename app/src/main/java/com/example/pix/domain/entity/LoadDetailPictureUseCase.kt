package com.example.pix.domain.entity

class LoadDetailPictureUseCase (
    private val repository: FlickrRepository
) {
    suspend operator fun invoke(): Picture {
        return repository.loadDetailPicture()
    }
}