package com.msk.model.search

import com.msk.model.common.Movie


data class MovieSearchResponse(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)