package com.msk.feature.search.data.util

internal object Constants {
    const val DEFAULT_PAGE = 1

    object SearchServiceFields {
        const val PAGE = "page"
        const val QUERY = "query"
    }

    object MovieSearchFields {
        const val PAGE = "page"
        const val RESULTS = "results"
        const val TOTAL_PAGES = "total_pages"
        const val TOTAL_RESULTS = "total_results"
    }

    object MovieFields {
        const val ADULT = "adult"
        const val BACKDROP_PATH = "backdrop_path"
        const val GENRE_IDS = "genre_ids"
        const val ID = "id"
        const val ORIGINAL_LANGUAGE = "original_language"
        const val ORIGINAL_TITLE = "original_title"
        const val OVERVIEW = "overview"
        const val POPULARITY = "popularity"
        const val POSTER_PATH = "poster_path"
        const val RELEASE_DATE = "release_date"
        const val TITLE = "title"
        const val VIDEO = "video"
        const val VOTE_AVERAGE = "vote_average"
        const val VOTE_COUNT = "vote_count"
    }

    object TrendingMoviesFields {
        const val PAGE = "page"
        const val RESULTS = "results"
        const val TOTAL_PAGES = "total_pages"
        const val TOTAL_RESULTS = "total_results"
    }

    object TrendingMovieFields {
        const val ADULT = "adult"
        const val BACKDROP_PATH = "backdrop_path"
        const val ID = "id"
        const val TITLE = "title"
        const val ORIGINAL_TITLE = "original_title"
        const val OVERVIEW = "overview"
        const val POSTER_PATH = "poster_path"
        const val MEDIA_TYPE = "media_type"
        const val ORIGINAL_LANGUAGE = "original_language"
        const val GENRE_IDS = "genre_ids"
        const val POPULARITY = "popularity"
        const val RELEASE_DATE = "release_date"
        const val VIDEO = "video"
        const val VOTE_AVERAGE = "vote_average"
        const val VOTE_COUNT = "vote_count"
    }
    object DiscoverResponseFields {
        const val PAGE = "page"
        const val RESULTS = "results"
        const val TOTAL_PAGES = "total_pages"
        const val TOTAL_RESULTS = "total_results"
    }
}