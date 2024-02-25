package com.example.homework_26.presentation.util.error_messages

import com.example.homework_26.data.common.ErrorType

fun getErrorMessage(errorType: ErrorType): String {
    return when (errorType) {
        ErrorType.SocketTimeout -> "The server did not respond in time."
        ErrorType.UnknownHost -> "The server could not be found."
        ErrorType.SSLHandshake -> "There was an error establishing a secure connection."
        ErrorType.Http -> "There was an error with the HTTP request."
        ErrorType.IO -> "There was an input/output error."
        ErrorType.FiltrationError -> "There was an error with the filtration."
        is ErrorType.UnknownError -> errorType.message
    }
}