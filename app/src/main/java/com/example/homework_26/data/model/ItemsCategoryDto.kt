package com.example.homework_26.data.model

import com.squareup.moshi.Json

data class ItemsCategoryDto(
    val id: String,
    val name: String,
    @Json(name = "name_de")
    val nameDe: String,
    val children: List<ItemsCategoryDto>
)