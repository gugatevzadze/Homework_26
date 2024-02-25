package com.example.homework_26.data.mapper

import com.example.homework_26.data.model.ItemsCategoryDto
import com.example.homework_26.domain.model.ItemsCategory

fun ItemsCategoryDto.toDomain(parentCount: Int = 0): ItemsCategory {
    return ItemsCategory(
        id = id,
        name = name,
        nameDe = nameDe,
        children = children.map { it.toDomain(parentCount + 1) },
        parentCount = parentCount
    )
}