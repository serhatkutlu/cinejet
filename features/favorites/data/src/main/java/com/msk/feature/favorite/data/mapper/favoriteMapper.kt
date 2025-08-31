package com.msk.feature.favorite.data.mapper

import com.msk.database.model.detail.MovieDetailEntity
import com.msk.model.detail.MovieDetail
import java.time.LocalDate


internal fun MovieDetailEntity.toMovieDetail(): MovieDetail {
    return MovieDetail(
        adult = adult,
        backdropPath = backdropPath,
        budget = budget,
        homepage = homepage,
        id = id,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate =
            if (releaseDate.isNullOrEmpty()) {
                LocalDate.now()
            } else {
                LocalDate.parse(releaseDate)
            },
        revenue = revenue,
        runtime = runtime.toString(),
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        casts =  emptyList(),
        genres =  emptyList(),
        isFavorite = isFavourite,
        images = null,
        originCountry = listOf(),
        recommendations =  emptyList(),
    )

}