package com.msk.network.calladapter

import com.msk.network.result.NetworkResult
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class CinejetCallAdapter(private val type:Type):CallAdapter<Type, Call<NetworkResult<Type>>> {
    override fun responseType(): Type {
        return type
    }

    override fun adapt(p0: Call<Type>): Call<NetworkResult<Type>> {
        return CinejetCall(p0)
    }


}