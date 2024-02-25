package com.example.homework_26.data.mapper

import com.example.homework_26.data.model.ItemsCategoryDto
import com.example.homework_26.domain.model.ItemsCategory

fun ItemsCategoryDto.toDomain(): ItemsCategory {
    return ItemsCategory(
        id = id,
        name = name,
        nameGerman = nameGerman,
        createdAt = createdAt,
        bglNumber = bglNumber,
        bglVariant = bglVariant,
        orderId = orderId,
        main = main,
        children = children.map { it.toDomain() }
    )
}