package com.example.homework_26.data.model

import com.squareup.moshi.Json

data class ItemsCategoryDto(
    val id: String,
    val name: String,
    @Json(name = "name_de")
    val nameGerman: String,
    val createdAt: String,
    @Json(name = "bgl_number")
    val bglNumber: String?,
    @Json(name = "bgl_variant")
    val bglVariant: String?,
    @Json(name = "order_id")
    val orderId: Int?,
    val main: String?,
    val children: List<ItemsCategoryDto>
)