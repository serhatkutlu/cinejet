package com.msk.network.interceptor

import com.msk.network.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val apiKey: String) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url.newBuilder()
            .addQueryParameter(API_KEY_QUERY_PARAM, apiKey)
            .build()

        val newRequest = request.newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }

    private companion object {
        private const val API_KEY_QUERY_PARAM = Constants.API_KEY_QUERY_PARAM
    }
}