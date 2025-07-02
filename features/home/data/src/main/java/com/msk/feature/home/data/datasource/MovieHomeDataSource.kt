package com.msk.feature.home.data.datasource


import com.msk.common.util.MediaType
import com.msk.feature.home.data.dto.MovieResponseDto
import com.msk.feature.home.data.service.MovieService
import com.msk.network.result.NetworkResult
import javax.inject.Inject

class MovieHomeDataSource @Inject constructor(private val movieService: MovieService){
    suspend fun fetchMovie(mediaType: MediaType, page: Int):NetworkResult<com.msk.feature.home.data.dto.MovieResponseDto>{
       return when(mediaType){
            MediaType.Upcoming -> movieService.fetchUpcoming(page)
            MediaType.TopRated -> movieService.fetchTopRated(page)
            MediaType.Popular -> movieService.fetchPopular(page)
            MediaType.NowPlaying -> movieService.fetchNowPlaying(page)
            MediaType.Discover -> movieService.fetchDiscover(page)
            MediaType.Trending -> movieService.fetchTrending(page)

        }
    }


}