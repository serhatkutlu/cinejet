package com.msk.domain.repository



import com.msk.common.util.Resource
import com.msk.model.common.MediaType
import com.msk.model.common.Movie
import kotlinx.coroutines.flow.Flow

interface MovieHomeRepository {
     fun loadMoviesByMediaType(mediaType: MediaType): Flow<Resource<List<Movie>>>

}