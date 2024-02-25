package com.example.homework_26.presentation.mapper

import com.example.homework_26.domain.model.ItemsCategory
import com.example.homework_26.presentation.model.ItemsCategoryModel

fun ItemsCategory.toPresentation(parentCount: Int = 0): ItemsCategoryModel {
    return ItemsCategoryModel(
        id = id,
        name = name,
        nameGerman = nameGerman,
        createdAt = createdAt,
        bglNumber = bglNumber,
        bglVariant = bglVariant,
        orderId = orderId,
        main = main,
        children = children.map { it.toPresentation(parentCount + 1) },
        parentCount = parentCount
    )
}