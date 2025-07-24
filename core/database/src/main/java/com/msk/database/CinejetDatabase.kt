package com.msk.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.msk.database.dao.seeall.MovieDao
import com.msk.database.dao.detail.MovieDetailDao
import com.msk.database.dao.seeall.MovieRemoteKeyDao
import com.msk.database.model.detail.MovieDetailEntity
import com.msk.database.model.seeall.MovieEntity
import com.msk.database.model.seeall.MovieRemoteKeyEntity
import com.msk.database.typeconverter.MediaTypeConverter


@Database(entities = [MovieEntity::class, MovieRemoteKeyEntity::class, MovieDetailEntity::class], version = 1, exportSchema = false)
@TypeConverters(MediaTypeConverter::class)
abstract class CinejetDatabase :RoomDatabase(){
    abstract val movieDao: MovieDao
    abstract val movieDetailDao: MovieDetailDao
   abstract val movieRemoteKeyDao: MovieRemoteKeyDao
}