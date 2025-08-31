package com.msk.network.di

import android.content.Context
import com.msk.network.BuildConfig
import com.msk.network.factory.RemoteFactory
import com.msk.network.interceptor.AuthInterceptor
import com.msk.network.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(remoteFactory: RemoteFactory, okHttpClient: OkHttpClient): Retrofit {
        return remoteFactory.createRetrofit(Constants.API_URL, okHttpClient)

    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        @ApplicationContext context: Context
    ): OkHttpClient {
        val cache = Cache(File(context.cacheDir, CACHE_CHILD), CACHE_SIZE)
        val builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.MINUTES)
            .writeTimeout(TIMEOUT, TimeUnit.MINUTES)
            .readTimeout(TIMEOUT, TimeUnit.MINUTES)
            .cache(cache)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val isDebug = BuildConfig.DEBUG
        return HttpLoggingInterceptor().apply {
            level = if (isDebug) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor(BuildConfig.TMDB_API_KEY)
    }

    private const val TIMEOUT = 10L
    private const val CACHE_CHILD = "http_cache"
    private const val CACHE_SIZE = 10L * 1024 * 1024

}