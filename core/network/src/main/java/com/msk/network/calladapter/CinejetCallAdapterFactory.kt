package com.msk.network.calladapter

import com.msk.network.result.NetworkResult
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

internal class CinejetCallAdapterFactory: CallAdapter.Factory() {
    override fun get(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {

        if (getRawType(type) != NetworkResult::class.java) return null
        check(type is ParameterizedType) {
            "CineJetCallAdapterFactory must be parameterized as CinemaxResult<Foo>"
        }
        val callType = getParameterUpperBound(0, type )

        return CinejetCallAdapter(callType)
    }
}