package com.example.homework_26.data.service

import com.example.homework_26.data.model.ItemsCategoryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemsApiService {
    @GET("6f722f19-3297-4edd-a249-fe765e8104db")
    suspend fun getItemsCategory(@Query("search") search: String): Response<List<ItemsCategoryDto>>
}