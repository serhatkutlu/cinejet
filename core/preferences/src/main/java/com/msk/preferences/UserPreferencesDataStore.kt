package com.msk.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.msk.design_system.util.ThemePreference
import com.msk.model.settings.AppLanguage
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


private val Context.dataStore by preferencesDataStore(name = "user_preferences")

@Singleton
class UserPreferencesDataStore @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dataStore: DataStore<Preferences>? = null
) {

    companion object {
        private val THEME_MODE_KEY = stringPreferencesKey("theme_mode")
        private val LANGUAGE_KEY = stringPreferencesKey("language")
    }

    private val ds: DataStore<Preferences>
        get() = dataStore ?: context.dataStore

    val themeMode: Flow<ThemePreference> = ds.data.map { preferences ->
        val value = preferences[THEME_MODE_KEY]
        value?.let { ThemePreference.valueOf(it) } ?: ThemePreference.SYSTEM
    }

    val language: Flow<AppLanguage> = ds.data.map { preferences ->
        val value = preferences[LANGUAGE_KEY]
        value?.let { code ->
            AppLanguage.entries.find { it.name == code }
        } ?: AppLanguage.ENGLISH
    }

    suspend fun setThemeMode(mode: ThemePreference) {
        ds.edit { prefs ->
            prefs[THEME_MODE_KEY] = mode.name
        }
    }

    suspend fun setLanguage(language: AppLanguage) {
        ds.edit { prefs ->
            prefs[LANGUAGE_KEY] = language.name
        }
    }

}