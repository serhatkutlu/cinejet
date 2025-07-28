package com.msk.database.di

import android.content.Context
import androidx.room.Room
import com.msk.database.CinejetDatabase
import com.msk.database.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideCineJetDatabase(@ApplicationContext context: Context): CinejetDatabase =
        Room.databaseBuilder(context, CinejetDatabase::class.java, Constants.Database.NAME)
            .fallbackToDestructiveMigration(true)
            .build()

    @Provides
    fun provideMovieDao(database: CinejetDatabase) = database.movieDao

    @Provides
    fun provideMovieDetailDao(database: CinejetDatabase) = database.movieDetailDao

    @Provides
    fun provideMovieRemoteKeyDao(database: CinejetDatabase) = database.movieRemoteKeyDao
}