package com.msk.common.util

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val errorCategory: ErrorCategory, val data:T?=null) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
}

