package com.msk.domain.repository



import androidx.paging.PagingData
import com.msk.common.util.MediaType
import com.msk.common.util.Resource
import com.msk.model.common.Movie
import kotlinx.coroutines.flow.Flow

interface MovieHomeRepository {
     fun loadMoviesByMediaType(mediaType: MediaType): Flow<Resource<List<Movie>>>

}