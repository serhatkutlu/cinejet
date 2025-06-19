package com.msk.database.util

internal object Constants {

    object Tables {
        const val MOVIE_TABLE_NAME="Movie"
        const val MOVIE_DETAIL_TABLE_NAME="MovieDetail"
        const val MOVIE_REMOTE_KEY="MovieRemoteKeys"

    }

    object Database{
        const val NAME="CineJetDatabase"
    }
    object Columns{
        const val ID="id"
        const val TITLE="title"
        const val OVERVIEW="overview"
        const val VOTE_AVERAGE="voteAverage"
        const val POSTER_PATH="posterPath"
        const val BACKDROP_PATH="backdropPath"
        const val MEDIA_TYPE="mediaType"
        const val RELEASE_DATE="releaseDate"
        const val PREV_PAGE="prevPage"
        const val NEXT_PAGE="NextPage"
        const val ADULT = "adult"
        const val BUDGET = "budget"
        const val GENRES = "genres"
        const val HOMEPAGE = "homepage"
        const val IMDB_ID = "imdbId"
        const val ORIGINAL_LANGUAGE = "originalLanguage"
        const val ORIGINAL_TITLE = "originalTitle"
        const val POPULARITY = "popularity"
        const val REVENUE = "revenue"
        const val RUNTIME = "runtime"
        const val STATUS = "status"
        const val TAGLINE = "tagline"
        const val VIDEO = "video"
        const val VOTE_COUNT = "voteCount"
        const val CAST = "cast"
        const val NAME = "name"
        const val LOGO_PATH = "logo_path"
        const val ORIGIN_COUNTRY = "origin_country"
        const val PROFILE_PATH = "profile_path"
        const val CHARACTER = "character"
        const val ORDER = "order"
        const val ORIGINAL_NAME = "original_name"
        const val LAST_FETCHED_TIME = "last_fetched_time"






    }
}