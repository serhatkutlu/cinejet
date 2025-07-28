package com.msk.core.data.datasource


import com.msk.core.data.dto.MovieResponseDto
import com.msk.core.data.service.MovieService
import com.msk.model.common.MediaType
import com.msk.network.result.NetworkResult
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val movieService: MovieService){
    suspend fun fetchMovie(mediaType: MediaType, page: Int):NetworkResult<MovieResponseDto>{
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