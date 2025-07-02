package com.msk.domain.repository



import androidx.paging.PagingData
import com.msk.common.util.MediaType
import com.msk.common.util.Resource
import com.msk.model.home.Movie
import kotlinx.coroutines.flow.Flow

interface MovieHomeRepository {
     fun loadMoviesByMediaType(mediaType: MediaType,maxPage:Int): Flow<Resource<List<Movie>>>

    fun loadMoviesByMediaTypePaging(mediaType: MediaType): Flow<PagingData<Movie>>
}