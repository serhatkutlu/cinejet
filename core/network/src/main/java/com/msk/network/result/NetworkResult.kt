package com.msk.network.result

import com.msk.common.util.ErrorCategory

sealed interface NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>

    data class Error<T>(
        val error: ErrorCategory = ErrorCategory.UnknownError
    ) : NetworkResult<T>
}

inline fun <T> NetworkResult<T>.onSuccess(action: (T) -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Success) action(data)
    return this
}

inline fun <T> NetworkResult<T>.onError(action: (ErrorCategory) -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Error) action(error)
    return this
}