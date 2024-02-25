package com.example.homework_26.di

import com.example.homework_26.data.common.ResponseHandler
import com.example.homework_26.data.repository.MainRepositoryImpl
import com.example.homework_26.data.service.ItemsApiService
import com.example.homework_26.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(
        responseHandler: ResponseHandler,
        apiService: ItemsApiService
    ): MainRepository {
        return MainRepositoryImpl(
            responseHandler = responseHandler,
            apiService = apiService
        )
    }
}