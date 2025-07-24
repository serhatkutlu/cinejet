package com.msk.features.see_all.domain.repository

import androidx.paging.PagingData
import com.msk.common.util.MediaType
import com.msk.model.common.Movie
import kotlinx.coroutines.flow.Flow

interface SeeAllRepository {


    fun loadMoviesByMediaTypePaging(mediaType: MediaType): Flow<PagingData<Movie>>
}