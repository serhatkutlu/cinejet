package com.msk.domain.repository


import androidx.paging.Pager
import androidx.paging.PagingData
import com.msk.common.util.MediaType
import com.msk.common.util.Resource
import com.msk.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieHomeRepository {
     suspend fun loadMoviesByMediaType(mediaType: MediaType): Flow<Resource<List<Movie>>>

    fun loadMoviesByMediaTypePaging(mediaType: MediaType): Flow<PagingData<Movie>>
}