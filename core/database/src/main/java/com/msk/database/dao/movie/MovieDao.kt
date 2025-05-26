package com.msk.database.dao.movie

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.msk.common.util.MediaType
import com.msk.database.model.MovieEntity
import com.msk.database.util.Constants.Tables.MOVIE_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM $MOVIE_TABLE_NAME WHERE mediaType = :mediaType LIMIT :limit")
    fun getMoviesByMediaTypeLimited(mediaType: MediaType, limit: Int): Flow<List<MovieEntity>>

    @Query("SELECT * FROM $MOVIE_TABLE_NAME WHERE mediaType = :mediaType")
    fun getPagingSourceByMediaType(mediaType: MediaType): PagingSource<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM $MOVIE_TABLE_NAME WHERE mediaType = :mediaType")
    suspend fun deleteMoviesByMediaType(mediaType: MediaType)


}