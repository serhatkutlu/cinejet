package com.msk.design_system.components


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import com.msk.design_system.util.CineJetImageLoader


@Composable
fun CineJetAsyncImage(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,

) {

    val context = LocalContext.current
    val imageLoader = remember { CineJetImageLoader.create(context) }

    AsyncImage(
        model = imageUrl,
        imageLoader = imageLoader,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier,

    )

}
