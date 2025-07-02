package com.msk.design_system.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource

import com.msk.design_system.theme.LocalCineJetSpacing

@Composable
fun IconWithText(
    iconId: ImageVector, text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(iconId, contentDescription = "Icon", tint = MaterialTheme.colorScheme.outline)

        CineJetText(
            modifier = Modifier.padding(start = LocalCineJetSpacing.current.small),
            text = text,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
 fun IconWithText(
    @DrawableRes iconId: Int, text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(iconId),
            contentDescription = "Icon",
            tint = MaterialTheme.colorScheme.outline
        )
        CineJetText(
            modifier = Modifier.padding(start = LocalCineJetSpacing.current.small),
            text = text,
            style = MaterialTheme.typography.bodySmall
        )
    }
}