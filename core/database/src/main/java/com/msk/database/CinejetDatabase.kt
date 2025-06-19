package com.msk.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.msk.database.dao.movie.MovieDao
import com.msk.database.dao.movie.MovieDetailDao
import com.msk.database.dao.movie.MovieRemoteKeyDao
import com.msk.database.model.MovieDetailEntity
import com.msk.database.model.MovieEntity
import com.msk.database.model.MovieRemoteKeyEntity
import com.msk.database.typeconverter.MediaTypeConverter
import com.msk.database.typeconverter.MovieDetailTypeConverter


@Database(entities = [MovieEntity::class, MovieRemoteKeyEntity::class,MovieDetailEntity::class], version = 1, exportSchema = false)
@TypeConverters(MediaTypeConverter::class, MovieDetailTypeConverter::class)
abstract class CinejetDatabase :RoomDatabase(){
    abstract val movieDao: MovieDao
    abstract val MovieDetailDao: MovieDetailDao
   abstract val movieRemoteKeyDao: MovieRemoteKeyDao
}