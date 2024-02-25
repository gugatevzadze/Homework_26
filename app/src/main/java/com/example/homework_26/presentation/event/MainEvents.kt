package com.example.homework_26.presentation.event

import com.example.homework_26.data.common.ErrorType

sealed class MainEvents {
    data class Search (val query: String?) : MainEvents()
    data object ClearSearch : MainEvents()
    data class UpdateErrorMessage(val errorMessage: ErrorType) : MainEvents()
}