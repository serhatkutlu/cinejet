package com.msk.feature.home.data.util

import com.msk.common.util.ErrorCategory
import com.msk.common.util.Resource
import com.msk.network.result.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
    crossinline fetch: suspend () -> NetworkResult<RequestType>,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline onFetchFailed: (Throwable) -> Unit = {}
): Flow<Resource<ResultType>> = flow {
    emit(Resource.Loading)

    val data = query().first()

    if (shouldFetch(data)) {

        try {

            when(val networkResponse = fetch()){
                is NetworkResult.Error -> {
                    onFetchFailed(Throwable(networkResponse.error.toString()))
                    emitAll(query().map { Resource.Error(networkResponse.error) })
                }
                is NetworkResult.Success -> {
                    saveFetchResult(networkResponse.data)
                    emitAll(query().map { Resource.Success(it) })
                }
            }

        } catch (throwable: Throwable) {
            onFetchFailed(throwable)
            emitAll(query().map { Resource.Error(ErrorCategory.fromThrowable(throwable)) })
        }
    } else {
        emitAll(query().map { Resource.Success(it) })
    }
}
