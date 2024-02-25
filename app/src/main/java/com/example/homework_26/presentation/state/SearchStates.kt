package com.example.homework_26.presentation.state

import com.example.homework_26.presentation.model.ItemsCategoryModel

data class SearchStates(
    val items: List<ItemsCategoryModel>? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
)
