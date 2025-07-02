package com.msk.feature.detail.data.util

internal object Constants {

    const val DEFAULT_REVIEWS_PAGE_SIZE = 20
    const val DEFAULT_LANGUAGE_CODE = "en"
    object MovieDetailQueryParams {
        const val VIDEOS = "videos"
        const val IMAGES = "images"
        const val CREDITS = "credits"
        const val RECOMMENDATIONS = "recommendations"

    }

    object JsonKeys {
        const val ADULT = "adult"
        const val BACKDROP_PATH = "backdrop_path"
        const val BELONGS_TO_COLLECTION = "belongs_to_collection"
        const val BUDGET = "budget"
        const val CREDITS = "credits"
        const val GENRES = "genres"
        const val HOMEPAGE = "homepage"
        const val ID = "id"
        const val NAME = "name"
        const val IMAGES = "images"
        const val IMDB_ID = "imdb_id"
        const val ORIGIN_COUNTRY = "origin_country"
        const val ORIGINAL_LANGUAGE = "original_language"
        const val ORIGINAL_TITLE = "original_title"
        const val OVERVIEW = "overview"
        const val POPULARITY = "popularity"
        const val POSTER_PATH = "poster_path"
        const val PRODUCTION_COMPANIES = "production_companies"
        const val PRODUCTION_COUNTRIES = "production_countries"
        const val RECOMMENDATIONS = "recommendations"
        const val RELEASE_DATE = "release_date"
        const val REVENUE = "revenue"
        const val RUNTIME = "runtime"
        const val SPOKEN_LANGUAGES = "spoken_languages"
        const val STATUS = "status"
        const val TAGLINE = "tagline"
        const val TITLE = "title"
        const val VIDEO = "video"
        const val VIDEOS = "videos"
        const val VOTE_AVERAGE = "vote_average"
        const val VOTE_COUNT = "vote_count"
    }
    object CastFields {
        const val ADULT = "adult"
        const val CAST_ID = "cast_id"
        const val CHARACTER = "character"
        const val CREDIT_ID = "credit_id"
        const val GENDER = "gender"
        const val ID = "id"
        const val KNOWN_FOR_DEPARTMENT = "known_for_department"
        const val NAME = "name"
        const val ORDER = "order"
        const val ORIGINAL_NAME = "original_name"
        const val POPULARITY = "popularity"
        const val PROFILE_PATH = "profile_path"
    }
    object ImagesFields {
        const val BACKDROPS = "backdrops"
        const val LOGOS = "logos"
        const val POSTERS = "posters"
    }
    object RecommendationsFields {
        const val PAGE = "page"
        const val RESULTS = "results"
        const val TOTAL_PAGES = "total_pages"
        const val TOTAL_RESULTS = "total_results"
    }
    object ResultFields {
        const val ADULT = "adult"
        const val BACKDROP_PATH = "backdrop_path"
        const val GENRE_IDS = "genre_ids"
        const val ID = "id"
        const val MEDIA_TYPE = "media_type"
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
    object AuthorDetailsFields {
        const val AVATAR_PATH = "avatar_path"
        const val NAME = "name"
        const val RATING = "rating"
        const val USERNAME = "username"
    }

    object ReviewFields {
        const val AUTHOR = "author"
        const val AUTHOR_DETAILS = "author_details"
        const val CONTENT = "content"
        const val CREATED_AT = "created_at"
        const val ID = "id"
        const val UPDATED_AT = "updated_at"
        const val URL = "url"
    }
    object MovieReviewsFields {
        const val ID = "id"
        const val PAGE = "page"
        const val RESULTS = "results"
        const val TOTAL_PAGES = "total_pages"
        const val TOTAL_RESULTS = "total_results"
    }
    object MovieVideosFields {
        const val ID = "id"
        const val RESULTS = "results"
    }
    object VideoFields {
        const val ISO_639_1 = "iso_639_1"
        const val ISO_3166_1 = "iso_3166_1"
        const val NAME = "name"
        const val KEY = "key"
        const val SITE = "site"
        const val SIZE = "size"
        const val TYPE = "type"
        const val OFFICIAL = "official"
        const val PUBLISHED_AT = "published_at"
        const val ID = "id"
    }

    object ImageItemFields {
        const val ASPECT_RATIO = "aspect_ratio"
        const val HEIGHT = "height"
        const val ISO_639_1 = "iso_639_1"
        const val FILE_PATH = "file_path"
        const val VOTE_AVERAGE = "vote_average"
        const val VOTE_COUNT = "vote_count"
        const val WIDTH = "width"
    }
}