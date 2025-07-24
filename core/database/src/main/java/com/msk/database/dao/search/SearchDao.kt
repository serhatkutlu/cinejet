package com.msk.database.dao.search

import androidx.room.Dao
import androidx.room.Query
import com.msk.database.model.seeall.MovieEntity
import com.msk.database.util.Constants
import com.msk.database.util.Constants.Tables.MOVIE_TABLE_NAME
import kotlinx.coroutines.flow.Flow


@Dao
interface SearchDao {

    @Query("SELECT * FROM $MOVIE_TABLE_NAME WHERE ${Constants.Columns.TITLE} LIKE '%' || :query || '%' ORDER BY ${Constants.Columns.LAST_FETCHED_TIME} DESC")
    fun searchMovies(query: String): Flow<List<MovieEntity>>




}