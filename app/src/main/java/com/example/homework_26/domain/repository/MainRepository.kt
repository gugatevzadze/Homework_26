package com.example.homework_26.domain.repository

import com.example.homework_26.data.common.Resource
import com.example.homework_26.domain.model.ItemsCategory
import kotlinx.coroutines.flow.Flow

interface MainRepository{
    suspend fun getItemsCategory(query:String): Flow<Resource<List<ItemsCategory>>>
}