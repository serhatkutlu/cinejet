package com.msk.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.msk.database.dao.detail.MovieDetailDao
import com.msk.database.dao.search.SearchDao
import com.msk.database.dao.seeall.MovieDao
import com.msk.database.dao.seeall.MovieRemoteKeyDao
import com.msk.database.model.detail.MovieDetailEntity
import com.msk.database.model.movie.MovieEntity
import com.msk.database.model.movie.MovieRemoteKeyEntity
import com.msk.database.typeconverter.ImagesTypeConverter
import com.msk.database.typeconverter.MediaTypeConverter
import com.msk.database.typeconverter.MovieDetailTypeConverter
import com.msk.database.typeconverter.RecommendationTypeConverter


@Database(entities = [MovieEntity::class, MovieRemoteKeyEntity::class, MovieDetailEntity::class], version = 6, exportSchema = false)
@TypeConverters(MediaTypeConverter::class,MovieDetailTypeConverter::class,ImagesTypeConverter::class,RecommendationTypeConverter::class)
abstract class CinejetDatabase :RoomDatabase(){
    abstract val movieDao: MovieDao
    abstract val movieDetailDao: MovieDetailDao
   abstract val movieRemoteKeyDao: MovieRemoteKeyDao
   abstract val movieSearchDao:SearchDao
}