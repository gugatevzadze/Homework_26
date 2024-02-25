package com.example.homework_26.presentation.model

data class ItemsCategoryModel(
    val id: String,
    val name: String,
    val nameGerman: String,
    val createdAt: String,
    val bglNumber: String?,
    val bglVariant: String?,
    val orderId: Int?,
    val main: String?,
    val children: List<ItemsCategoryModel>,
    val parentCount: Int
)