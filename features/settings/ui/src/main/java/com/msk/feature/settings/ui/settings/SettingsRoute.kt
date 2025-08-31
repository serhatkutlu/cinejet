package com.msk.feature.settings.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.msk.design_system.components.CineJetConfirmDialog
import com.msk.design_system.components.CineJetText
import com.msk.design_system.theme.LocalCineJetSpacing
import com.msk.design_system.util.ThemePreference
import com.msk.feature.settings.ui.R
import com.msk.feature.settings.ui.settings.companents.ClearCacheItem
import com.msk.feature.settings.ui.settings.companents.SegmentedControl
import com.msk.feature.settings.ui.settings.companents.SettingDropdownItems
import com.msk.model.settings.AppLanguage

@Composable
fun SettingsRoute(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    var currentDialogEffect by remember { mutableStateOf<SettingsUiEffect?>(null) }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect {
            currentDialogEffect = it
        }
    }


    SettingsScreen(
        language = state.language,
        showLanguageDialog = { language, message ->
            viewModel.onEvent(
                SettingsUiEvent.ShowLanguageDialog(
                    language,
                    message
                )
            )
        },
        onThemeToggle = { viewModel.onEvent(SettingsUiEvent.ToggleTheme(it)) },
        themePreference = state.themePreference,
        showClearCacheDialog = { viewModel.onEvent(SettingsUiEvent.ShowClearCacheDialog(it)) }

    )

    if (currentDialogEffect is SettingsUiEffect.ShowLanguageDialog) {
        val languageDialogState = currentDialogEffect as SettingsUiEffect.ShowLanguageDialog
        CineJetConfirmDialog(
            message = stringResource(languageDialogState.message),
            onConfirm = {
                viewModel.onEvent(SettingsUiEvent.SelectLanguage(languageDialogState.language))
                currentDialogEffect = null
            },
            onDismiss = { currentDialogEffect = null },
        )
    } else if (currentDialogEffect is SettingsUiEffect.ShowClearCacheDialog) {
        val clearCacheDialogState = currentDialogEffect as SettingsUiEffect.ShowClearCacheDialog
        CineJetConfirmDialog(
            message = stringResource(clearCacheDialogState.message),
            onConfirm = {
                viewModel.onEvent(SettingsUiEvent.ClearCache)
                currentDialogEffect = null
            },
            isAlert = true,
            onDismiss = { currentDialogEffect = null },
        )
    }
}


@Composable
fun SettingsScreen(
    language: AppLanguage,
    themePreference: ThemePreference,
    showLanguageDialog: (AppLanguage, Int) -> Unit,
    onThemeToggle: (ThemePreference) -> Unit = {},
    showClearCacheDialog: (Int) -> Unit = {}
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalCineJetSpacing.current.large),
        verticalArrangement = Arrangement.spacedBy(LocalCineJetSpacing.current.medium)
    ) {
        item {
            SettingDropdownItems(
                title = stringResource(R.string.language),
                options = AppLanguage.entries,
                selected = language.name,
                onOptionSelected = {
                    showLanguageDialog(it, R.string.language_dialog_message)
                }
            )
        }


        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CineJetText(stringResource(R.string.theme))
                SegmentedControl(
                    selected = themePreference,
                    options = ThemePreference.entries,
                    labelProvider = { pref -> stringResource(pref.value) },
                    onSelectedChange = { onThemeToggle(it) }
                )

            }

        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CineJetText(stringResource(R.string.clear_cache))
                ClearCacheItem {
                    showClearCacheDialog(R.string.clear_cache_dialog_message)
                }
            }
        }

    }
}


