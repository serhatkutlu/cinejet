package com.msk.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.msk.core.design_system.R
import com.msk.design_system.theme.LocalCineJetSpacing

@Composable
fun CineJetConfirmDialog(
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    isAlert: Boolean = false,
    confirmText: String = stringResource(R.string.ok),
    dismissText: String = stringResource(R.string.cancel)
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(20.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (isAlert){
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(48.dp)
                    )

                }
                Spacer(modifier = Modifier.height(LocalCineJetSpacing.current.small))
                CineJetText(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(LocalCineJetSpacing.current.medium))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CineJetText(
                        text = dismissText,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { onDismiss() }
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                            .padding(horizontal = 24.dp, vertical = 12.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                    CineJetText(
                        text = confirmText,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { onConfirm() }
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(horizontal = 24.dp, vertical = 12.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun confirmDialogPreview() {
    CineJetConfirmDialog(
        message = "Are you sure you want to change the app language?",
        onConfirm = {},
        onDismiss = {}
    )
}
