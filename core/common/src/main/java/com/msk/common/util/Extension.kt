package com.msk.common.util

import com.msk.common.util.Constants.IMAGE_BASE_URL

fun getPosterUrl(posterPath: String?, size: TmdbPosterSize=TmdbPosterSize.W500): String? {
    return posterPath?.let { "$IMAGE_BASE_URL${size.value}$posterPath" }
}
