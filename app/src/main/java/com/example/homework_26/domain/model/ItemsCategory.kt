package com.example.homework_26.domain.model

data class ItemsCategory(
    val id: String,
    val name: String,
    val nameDe: String,
    val children: List<ItemsCategory>,
    val parentCount: Int
)