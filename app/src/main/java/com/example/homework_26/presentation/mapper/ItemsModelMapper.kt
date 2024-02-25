package com.example.homework_26.presentation.mapper

import com.example.homework_26.domain.model.ItemsCategory
import com.example.homework_26.presentation.model.ItemsCategoryModel

fun ItemsCategory.toPresentation(): ItemsCategoryModel {
    return ItemsCategoryModel(
        id = id,
        name = name,
        nameDe = nameDe,
        children = children.map { it.toPresentation() },
        parentCount = parentCount
    )
}