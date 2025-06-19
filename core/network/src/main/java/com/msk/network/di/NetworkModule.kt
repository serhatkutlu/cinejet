package com.msk.network.di

import com.msk.network.BuildConfig
import com.msk.network.factory.RemoteFactory
import com.msk.network.interceptor.AuthInterceptor
import com.msk.network.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(remoteFactory: RemoteFactory,authInterceptor: AuthInterceptor): Retrofit{
        return remoteFactory.createRetrofit(Constants.API_URL,authInterceptor, BuildConfig.DEBUG)

    }

    @Provides
    fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor(BuildConfig.TMDB_API_KEY)
    }

}