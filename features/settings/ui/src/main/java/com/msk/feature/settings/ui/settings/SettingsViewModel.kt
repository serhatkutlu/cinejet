package com.msk.feature.settings.ui.settings

import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import com.msk.cache.CacheCleaner
import com.msk.design_system.base.viewmodel.BaseViewModel
import com.msk.design_system.util.ThemePreference
import com.msk.model.settings.AppLanguage
import com.msk.preferences.UserPreferencesDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val themePreferences: UserPreferencesDataStore,private val cacheCleaner: CacheCleaner) :
    BaseViewModel<SettingsUiState, SettingsUiEvent, SettingsUiEffect>(SettingsUiState()) {

        init {
            getInitValue()
        }

    private fun getInitValue() {
        viewModelScope.launch {
            themePreferences.themeMode.collect {
                updateState { copy(themePreference = it) }
            }
        }
        viewModelScope.launch {
            themePreferences.language.collect {
                updateState { copy(language = it) }
            }
        }
    }

    private fun clearCache() {
        viewModelScope.launch {
            cacheCleaner.clearCache()
        }
    }
    override fun onEvent(event: SettingsUiEvent) {
        when (event) {
            is SettingsUiEvent.ToggleTheme -> {
                viewModelScope.launch {
                    themePreferences.setThemeMode(event.theme)

                }
                updateState {
                    copy(themePreference = event.theme)
                }
            }

            is SettingsUiEvent.SelectLanguage -> {
                viewModelScope.launch {
                    themePreferences.setLanguage(event.language)
                }
                updateState { copy(language = event.language) }
            }

            is SettingsUiEvent.ClearCache -> {
                clearCache()
            }

            is SettingsUiEvent.ShowLanguageDialog -> setEffect { SettingsUiEffect.ShowLanguageDialog(event.language,event.message) }
            is SettingsUiEvent.ShowClearCacheDialog -> setEffect { SettingsUiEffect.ShowClearCacheDialog(event.message)}
        }
    }
}


data class SettingsUiState(
    val language: AppLanguage = AppLanguage.ENGLISH,
    val themePreference: ThemePreference = ThemePreference.SYSTEM,

    )

sealed interface SettingsUiEvent {
    data class ToggleTheme(val theme: ThemePreference) : SettingsUiEvent
    data class SelectLanguage(val language: AppLanguage) : SettingsUiEvent
    data object ClearCache : SettingsUiEvent
    data class ShowClearCacheDialog(@StringRes val message: Int) : SettingsUiEvent
    data class ShowLanguageDialog(val language: AppLanguage,@StringRes val message: Int) : SettingsUiEvent
}

sealed interface SettingsUiEffect{
    data  class ShowLanguageDialog(val language: AppLanguage,@StringRes val message: Int): SettingsUiEffect
    data class ShowClearCacheDialog(@StringRes val message: Int): SettingsUiEffect
}


