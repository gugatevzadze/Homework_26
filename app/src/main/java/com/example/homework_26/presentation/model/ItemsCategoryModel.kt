package com.example.homework_26.presentation.model

data class ItemsCategoryModel(
    val id: String,
    val name: String,
    val nameDe: String,
    val children: List<ItemsCategoryModel>,
    val parentCount: Int
)