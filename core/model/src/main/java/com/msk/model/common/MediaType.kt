package com.msk.model.common


enum class MediaType(val mediaType: String) {
    Upcoming(UpcomingMediaType),
    TopRated(TopRatedMediaType),
    Popular(PopularMediaType),
    NowPlaying(NowPlayingMediaType),
    Discover(DiscoverMediaType),
    Trending(TrendingMediaType);
}

private const val UpcomingMediaType = "upcoming"
private const val TopRatedMediaType = "top_rated"
private const val PopularMediaType = "popular"
private const val NowPlayingMediaType = "now_playing"
private const val DiscoverMediaType = "discover"
private const val TrendingMediaType = "trending"
