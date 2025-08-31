package com.msk.database.dao.search

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.msk.database.model.detail.MovieDetailEntity
import com.msk.database.model.movie.MovieEntity
import com.msk.database.util.Constants
import com.msk.database.util.Constants.Tables.MOVIE_DETAIL_TABLE_NAME
import com.msk.database.util.Constants.Tables.MOVIE_TABLE_NAME
import kotlinx.coroutines.flow.Flow


@Dao
interface SearchDao {

    @Query("SELECT * FROM $MOVIE_DETAIL_TABLE_NAME WHERE ${Constants.Columns.TITLE} LIKE '%' || :query || '%' GROUP BY  title ORDER BY ${Constants.Columns.POPULARITY} DESC")
    fun searchMovies(query: String): PagingSource<Int, MovieDetailEntity>

}