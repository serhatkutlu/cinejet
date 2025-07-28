package com.msk.feature.search.data.mapper

import com.msk.database.model.seeall.MovieEntity
import com.msk.feature.search.data.dto.MovieDto
import com.msk.feature.search.data.dto.MovieSearchResponseDto
import com.msk.model.common.MediaType
import com.msk.model.common.Movie
import com.msk.model.search.MovieSearchResponse

internal fun MovieDto.toDomains(): Movie {
    return Movie(
        id = id,
        title = title,
        posterPath = posterPath,
        overview = overview,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        backdropPath = backdropPath,
        lastFetchedTime = null,
        mediaType = null,
    )
}


internal fun MovieSearchResponseDto.toDomain(): MovieSearchResponse {
    return MovieSearchResponse(
        page = page,
        results = results.map { it.toDomain() },
        totalPages = totalPages,
        totalResults = totalResults
    )
}


internal fun MovieEntity.toDomainModel(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        overview = this.overview,
        voteAverage = this.voteAverage,
        posterPath = this.posterPath,
        backdropPath = this.backdropPath,
        releaseDate = this.releaseDate,
        mediaType = this.mediaType,
        lastFetchedTime = this.lastFetchedTime

    )

}

internal fun MovieDto.toEntity(mediaType: MediaType): MovieEntity {
    return MovieEntity(
        networkId = this.id,
        mediaType = mediaType,
        title = this.title,
        overview = this.overview,
        voteAverage = this.voteAverage,
        posterPath = this.posterPath,
        backdropPath = this.backdropPath,
        releaseDate = this.releaseDate,
        lastFetchedTime = System.currentTimeMillis()
    )
}

internal fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        posterPath = posterPath,
        overview = overview,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        backdropPath = backdropPath,
        lastFetchedTime = null,
        mediaType = null,
    )
}