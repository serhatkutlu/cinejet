package com.msk.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.msk.database.util.Constants


@Entity(tableName = Constants.Tables.MOVIE_DETAIL_TABLE_NAME)
data class MovieDetailEntity (
@PrimaryKey(autoGenerate = false)
@ColumnInfo(name = Constants.Columns.ID)
val id: Int,

@ColumnInfo(name = Constants.Columns.ADULT)
val adult: Boolean,

@ColumnInfo(name = Constants.Columns.BACKDROP_PATH)
val backdropPath: String?,

@ColumnInfo(name = Constants.Columns.BUDGET)
val budget: Int,

@ColumnInfo(name = Constants.Columns.GENRES)
val genres: List<Genre>,

@ColumnInfo(name = Constants.Columns.HOMEPAGE)
val homepage: String?,

@ColumnInfo(name = Constants.Columns.IMDB_ID)
val imdbId: String?,

@ColumnInfo(name = Constants.Columns.ORIGINAL_LANGUAGE)
val originalLanguage: String,

@ColumnInfo(name = Constants.Columns.ORIGINAL_TITLE)
val originalTitle: String,

@ColumnInfo(name = Constants.Columns.OVERVIEW)
val overview: String,

@ColumnInfo(name = Constants.Columns.POPULARITY)
val popularity: Double,

@ColumnInfo(name = Constants.Columns.POSTER_PATH)
val posterPath: String?,

@ColumnInfo(name = Constants.Columns.RELEASE_DATE)
val releaseDate: String?,

@ColumnInfo(name = Constants.Columns.REVENUE)
val revenue: Long,

@ColumnInfo(name = Constants.Columns.RUNTIME)
val runtime: Int?,

@ColumnInfo(name = Constants.Columns.STATUS)
val status: String,

@ColumnInfo(name = Constants.Columns.TAGLINE)
val tagline: String?,

@ColumnInfo(name = Constants.Columns.TITLE)
val title: String,

@ColumnInfo(name = Constants.Columns.VIDEO)
val video: Boolean,

@ColumnInfo(name = Constants.Columns.VOTE_AVERAGE)
val voteAverage: Double,

@ColumnInfo(name = Constants.Columns.VOTE_COUNT)
val voteCount: Int,

@ColumnInfo(name = Constants.Columns.CAST)
val cast: List<Cast>
)