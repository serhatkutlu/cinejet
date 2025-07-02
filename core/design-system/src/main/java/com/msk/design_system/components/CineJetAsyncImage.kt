package com.msk.design_system.components


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

import coil.compose.AsyncImage
import com.msk.core.design_system.R


@Composable
fun CineJetAsyncImage(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    placeholder: Int = R.drawable.placeholder,
    error: Int = R.drawable.error
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier,
       // placeholder = painterResource(id = placeholder),
       // error = painterResource(id = error)
    )

}
