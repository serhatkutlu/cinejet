package com.msk.common.util

import com.msk.common.util.Constants.IMAGE_BASE_URL
import kotlin.time.Duration.Companion.days


fun formatRuntime(runtime: Int): String {
    val hours = runtime / 60
    val minutes = runtime % 60
    return if (hours > 0) "${hours}h ${minutes}m" else "${minutes}m"
}

fun String.parseImageUrl(size: TmdbPosterSize = TmdbPosterSize.W500): String {
    return ("$IMAGE_BASE_URL${size.value}$this")
}

fun isDataStale(movie: Long?): Boolean {
    val cacheTime = 1.days.inWholeMilliseconds
    val currentTime = System.currentTimeMillis()
    val lastFetchedTime = movie ?: 0
    return (currentTime - lastFetchedTime) > cacheTime
}

