package com.example.pix.di

import com.example.pix.data.flickr.ApiService
import com.example.pix.data.repository.FlickrRepositoryImpl
import com.example.pix.domain.entity.FlickrRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): FlickrRepository {
        return FlickrRepositoryImpl(apiService)
    }
}