package com.msk.feature.settings.ui.settings.companents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msk.design_system.components.CineJetText

@Composable
fun ClearCacheItem(onClearCache: () -> Unit) {


    Column {
        // Ayar satırı
        Row(
            modifier = Modifier
                .clickable { onClearCache() }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Clear Cache",
                tint = MaterialTheme.colorScheme.error
            )
            Spacer(Modifier.width(16.dp))
            CineJetText(
                text = "Önbelleği Temizle",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )
        }

    }

}
