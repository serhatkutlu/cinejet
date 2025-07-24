package com.msk.feature.domain.repository

import androidx.paging.PagingData
import com.msk.common.util.MediaType
import com.msk.common.util.Resource
import com.msk.model.common.Movie
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun loadMoviesByMediaType(mediaType: MediaType): Flow<Resource<List<Movie>>>

    fun searchMovies(query: String):Flow<PagingData<Movie>>

}