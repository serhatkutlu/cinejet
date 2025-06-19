package com.msk.network.calladapter

import com.msk.network.result.NetworkResult
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

internal class CinejetCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        val rawReturnType: Class<*> = getRawType(type)
        if (rawReturnType == Call::class.java && type is ParameterizedType) {
            val callInnerType: Type = getParameterUpperBound(0, type)
            if (getRawType(callInnerType) == NetworkResult::class.java) {
                if (callInnerType is ParameterizedType) {
                    val resultInnerType = getParameterUpperBound(0, callInnerType)
                    return CinejetCallAdapter(resultInnerType)
                }
                return CinejetCallAdapter(Nothing::class.java)
            }
        }
        return CinejetCallAdapter(Nothing::class.java)

    }
}