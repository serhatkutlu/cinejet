package com.msk.data.mapper

import com.msk.common.util.MediaType
import com.msk.data.dto.MovieDto
import com.msk.database.model.MovieEntity
import com.msk.model.Movie

internal fun MovieDto.toEntity(mediaType: MediaType): MovieEntity {
    return MovieEntity(
        id = this.id,
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