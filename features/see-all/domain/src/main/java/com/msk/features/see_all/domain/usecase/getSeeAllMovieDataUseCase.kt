package com.msk.features.see_all.domain.usecase

import androidx.paging.PagingData
import com.msk.common.util.MediaType
import com.msk.features.see_all.domain.repository.SeeAllRepository
import com.msk.model.common.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class getSeeAllMovieDataUseCase @Inject constructor(private val repository: SeeAllRepository){
    operator fun invoke(
        mediaType: MediaType,
    ): Flow<PagingData<Movie>>{
        return repository.loadMoviesByMediaTypePaging(mediaType)
    }
}