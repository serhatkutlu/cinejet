package com.msk.common.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline onFetchFailed: (Throwable) -> Unit = {}
): Flow<Resource<ResultType>> = flow {
    emit(Resource.Loading)

    val data = query().first()

    if (shouldFetch(data)) {
        emit(Resource.Loading)

        try {
            val networkResponse = fetch()
            saveFetchResult(networkResponse)
            emitAll(query().map { Resource.Success(it) })
        } catch (throwable: Throwable) {
            onFetchFailed(throwable)
            emitAll(query().map { Resource.Error(ErrorCategory.fromThrowable(throwable), it) })
        }
    } else {
        emitAll(query().map { Resource.Success(it) })
    }
}
