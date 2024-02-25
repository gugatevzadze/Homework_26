package com.example.homework_26.data.common

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val errorType: ErrorType) : Resource<Nothing>()
    data class Loading<Nothing>(val loading: Boolean) : Resource<Nothing>()
}
