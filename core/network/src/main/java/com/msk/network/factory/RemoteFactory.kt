package com.msk.network.factory

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.msk.network.calladapter.CinejetCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RemoteFactory @Inject constructor() {
    fun createRetrofit(
        url: String,
        okHttpClient:OkHttpClient,
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(defaultJson.asConverterFactory(mimeTypeJson))
            .addCallAdapterFactory(CinejetCallAdapterFactory())
            .build()
    }
    private companion object {

        val defaultJson = Json {
            ignoreUnknownKeys = true
            explicitNulls = false
            coerceInputValues = true

        }
         val mimeTypeJson = "application/json".toMediaType()
    }

}

