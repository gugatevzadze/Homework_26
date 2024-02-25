package com.example.homework_26.data.repository

import com.example.homework_26.data.common.Resource
import com.example.homework_26.data.common.ResponseHandler
import com.example.homework_26.data.mapper.base.mapToDomain
import com.example.homework_26.data.mapper.toDomain
import com.example.homework_26.data.service.ItemsApiService
import com.example.homework_26.domain.model.ItemsCategory
import com.example.homework_26.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val responseHandler: ResponseHandler,
    private val apiService: ItemsApiService
) : MainRepository {
    override suspend fun getItemsCategory(query:String): Flow<Resource<List<ItemsCategory>>> {
        return responseHandler.safeApiCall {
            apiService.getItemsCategory(query)
        }.mapToDomain { itemsCategoryDto ->
            itemsCategoryDto.map { it.toDomain() }
        }
    }
}