package com.msk.feature.seeall.data.mapper

import com.msk.common.util.MediaType
import com.msk.core.data.dto.MovieDto
import com.msk.database.model.seeall.MovieEntity
import com.msk.model.common.Movie

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

internal fun MovieEntity.toDomainModel(): Movie {
    return Movie(
        id = this.networkId,
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