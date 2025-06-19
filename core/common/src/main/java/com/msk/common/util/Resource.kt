package com.msk.common.util

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error<out T>(val errorCategory: ErrorCategory, val data: T? = null) : Resource<T>()
    data object Loading : Resource<Nothing>()
}
