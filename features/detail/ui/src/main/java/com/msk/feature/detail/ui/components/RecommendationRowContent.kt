package com.msk.feature.detail.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import com.msk.design_system.components.MovieCard
import com.msk.design_system.theme.LocalCineJetSpacing
import com.msk.model.detail.Recommendation

@Composable
fun  RecommendationRowContent( recommendations:List<Recommendation>, onRecommendationClick: (Int) -> Unit){

    LazyRow(contentPadding = PaddingValues(horizontal = LocalCineJetSpacing.current.medium),    horizontalArrangement = Arrangement.spacedBy(LocalCineJetSpacing.current.medium)
    ) {
        items(recommendations.size) { index ->
            val recommendation = recommendations[index]

            MovieCard(
                onClick = { onRecommendationClick(recommendation.id) } ,
                title = recommendation.title,
                posterPath = recommendation.posterPath,
                voteAverage = recommendation.voteAverage
            )
        }
    }
}
