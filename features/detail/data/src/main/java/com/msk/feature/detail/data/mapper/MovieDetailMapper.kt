package com.msk.feature.detail.data.mapper

import com.msk.feature.detail.data.dto.AuthorDetailsDto
import com.msk.feature.detail.data.dto.CastDto
import com.msk.feature.detail.data.dto.GenreDto
import com.msk.feature.detail.data.dto.ImageItemDto
import com.msk.feature.detail.data.dto.ImagesDto
import com.msk.feature.detail.data.dto.MovieDetailDto
import com.msk.feature.detail.data.dto.MovieVideosDto
import com.msk.feature.detail.data.dto.ResultDto
import com.msk.feature.detail.data.dto.ReviewDto
import com.msk.feature.detail.data.util.Constants.DEFAULT_LANGUAGE_CODE
import com.msk.model.detail.AuthorDetails
import com.msk.model.detail.Cast
import com.msk.model.detail.Genre
import com.msk.model.detail.ImageItem
import com.msk.model.detail.Images
import com.msk.model.detail.MovieDetail
import com.msk.model.detail.MovieVideo
import com.msk.model.detail.Recommendation
import com.msk.model.detail.Review
import java.time.LocalDate
import java.time.LocalDateTime


internal fun MovieDetailDto.toMovieDetail(): MovieDetail {
    return MovieDetail(
        adult = adult,
        backdropPath = backdropPath,
        budget = budget,
        casts = credits.cast.map { it.toCast() },
        genres = genres.map { it.toGenre() },
        homepage = homepage,
        id = id,
        images = images.toImages(),
        imdbId = imdbId,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        recommendations = recommendations.results.map { it.toRecommendation() },
        releaseDate = LocalDate.parse(releaseDate),
        revenue = revenue,
        runtime = runtime.toString(),
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

private fun ResultDto.toRecommendation(): Recommendation {
    return Recommendation(
        id = id,
        voteCount = voteCount,
        voteAverage = voteAverage,
        title = title,
        posterPath = posterPath
    )
}

private fun ImagesDto.toImages(): Images {
    return Images(
        backdrops = backdrops.map { it.toImageItem() },
        posters = posters.filter { it.iso6391?.equals(DEFAULT_LANGUAGE_CODE) ?: false }
            .map { it.toImageItem() }
    )
}

private fun ImageItemDto.toImageItem(): ImageItem {
    return ImageItem(
        aspectRatio = aspectRatio,
        height = height,
        filePath = filePath,
        language = iso6391,
        voteAverage = voteAverage,
        voteCount = voteCount,
        width = width
    )
}

private fun CastDto.toCast(): Cast {
    return Cast(
        id = id,
        name = name,
        character = character,
        profilePath = profilePath,
        originalName = originalName,
        knownForDepartment = knownForDepartment
    )
}

private fun GenreDto.toGenre(): Genre {
    return Genre(
        id = id,
        name = name
    )
}

internal fun MovieVideosDto.toMovieVideo(): List<MovieVideo> {
    return this.results.map {
        val movieVideo = MovieVideo(
            name = it.name,
            videoKey = it.key,
            site = it.site,
            size = it.size,
            type = it.type,
            isOfficial = it.official,
            id = it.id
        )
        movieVideo
    }
}



internal fun ReviewDto.toReview(): Review {
    return Review(
        author = author,
        content = content,
        id = id,
        url = url,
        createdAt = createdAt,
        updatedAt = updatedAt,
        authorDetails = authorDetails.toAuthorDetails()
    )
}

private fun AuthorDetailsDto.toAuthorDetails(): AuthorDetails {
    return AuthorDetails(
        name = name,
        username = username,
        avatarPath = avatarPath,
        rating = rating
    )
}