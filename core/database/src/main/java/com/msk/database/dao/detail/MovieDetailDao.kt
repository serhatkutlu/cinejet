package com.msk.database.dao.detail

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.msk.database.model.detail.MovieDetailEntity
import com.msk.database.util.Constants
import com.msk.database.util.Constants.Tables.MOVIE_DETAIL_TABLE_NAME
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDetailDao {

    @Query("SELECT * FROM $MOVIE_DETAIL_TABLE_NAME WHERE id = :id")
    fun getById(id: Int): Flow<MovieDetailEntity?>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieDetails: MovieDetailEntity)

    @Query("DELETE FROM $MOVIE_DETAIL_TABLE_NAME WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("UPDATE  $MOVIE_DETAIL_TABLE_NAME SET ${Constants.Columns.IS_FAVOURITE} =:isFavorite WHERE id = :id")
    suspend fun updateFavoriteById(id: Long, isFavorite: Boolean)
}