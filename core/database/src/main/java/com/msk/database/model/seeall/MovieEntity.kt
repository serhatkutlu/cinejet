package com.msk.database.model.seeall

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.msk.common.util.MediaType
import com.msk.database.util.Constants

@Entity(tableName = Constants.Tables.MOVIE_TABLE_NAME)
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.Columns.ID)
    val id: Int = 0,

    val networkId: Int,

    @ColumnInfo(name = Constants.Columns.MEDIA_TYPE)
    val mediaType: MediaType,

    @ColumnInfo(name = Constants.Columns.TITLE)
    val title: String,

    @ColumnInfo(name = Constants.Columns.OVERVIEW)
    val overview: String,

    @ColumnInfo(name = Constants.Columns.VOTE_AVERAGE)
    val voteAverage: Double,

    @ColumnInfo(name = Constants.Columns.POSTER_PATH)
    val posterPath: String?,

    @ColumnInfo(name = Constants.Columns.BACKDROP_PATH)
    val backdropPath: String?,

    @ColumnInfo(name = Constants.Columns.RELEASE_DATE)
    val releaseDate: String?,

    @ColumnInfo(name = Constants.Columns.LAST_FETCHED_TIME)
    val lastFetchedTime: Long = System.currentTimeMillis()
)

