package com.msk.network.calladapter

import com.msk.network.result.NetworkResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class CinejetCall <T>(proxy: Call<T>): CallDelegate<T, NetworkResult<T>>(proxy){
    override fun enqueueImpl(callback: Callback<NetworkResult<T>>) {
        proxy.enqueue(object :Callback<T>{
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val networkResult = handleApiNetworkResult(response)
                callback.onResponse(this@CinejetCall, Response.success(networkResult))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val networkResult= NetworkResult.Error<T>(error = com.msk.common.util.ErrorCategory.fromThrowable(t))
                callback.onResponse(this@CinejetCall, Response.success(networkResult))
            }

        })

    }

    private fun handleApiNetworkResult(response: Response<T>): NetworkResult<T> {
        return try {
            if (response.isSuccessful) {
                response.body()?.let {
                    NetworkResult.Success(it)
                } ?: NetworkResult.Error(error = com.msk.common.util.ErrorCategory.UnknownError)
            } else {
                NetworkResult.Error(com.msk.common.util.ErrorCategory.fromHttpCode(response.code()))
            }
        } catch (e: Exception) {
            NetworkResult.Error(error = com.msk.common.util.ErrorCategory.fromThrowable(e))
        }
    }


    override fun cloneImpl(): Call<NetworkResult<T>> {
       return CinejetCall(proxy.clone())

    }


}