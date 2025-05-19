package com.msk.network.result

import com.msk.common.util.ErrorCategory

sealed interface NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>

    data class Error<T>(
        val error: ErrorCategory = ErrorCategory.UnknownError
    ) : NetworkResult<T>
}

