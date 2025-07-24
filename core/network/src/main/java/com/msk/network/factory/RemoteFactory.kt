package com.msk.network.factory

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.msk.network.calladapter.CinejetCallAdapterFactory
import com.msk.network.interceptor.AuthInterceptor
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Singleton
class RemoteFactory @Inject constructor() {
    fun createRetrofit(
        url: String,
        authInterceptor: AuthInterceptor,
        isDebug: Boolean
    ): Retrofit {
        val loggingInterceptor = provideLoggingInterceptor(isDebug)
        val client = makeOkHttpClient(
            authInterceptor = authInterceptor,
            loggingInterceptor = loggingInterceptor
        )

        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(defaultJson.asConverterFactory(mimeTypeJson))
            .addCallAdapterFactory(CinejetCallAdapterFactory())
            .build()
    }

    private fun makeOkHttpClient(
        authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.MINUTES)
            .writeTimeout(TIMEOUT, TimeUnit.MINUTES)
            .readTimeout(TIMEOUT, TimeUnit.MINUTES)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
        return builder.build()
    }

    private fun provideLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (isDebug) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    private companion object {
        const val TIMEOUT = 10L

        val defaultJson = Json {
            ignoreUnknownKeys = true
            explicitNulls = false
            coerceInputValues = true

        }
        val mimeTypeJson = "application/json".toMediaType()
    }

}

