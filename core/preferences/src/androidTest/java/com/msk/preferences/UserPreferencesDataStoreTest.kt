package com.msk.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.msk.design_system.util.ThemePreference
import com.msk.model.settings.AppLanguage
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

@RunWith(AndroidJUnit4::class)
class UserPreferencesDataStoreTest {

    private lateinit var userPrefs: UserPreferencesDataStore
    private lateinit var testDataStore: DataStore<Preferences>
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()

        testDataStore = PreferenceDataStoreFactory.create(
            produceFile = { File.createTempFile("test_prefs", ".preferences_pb") }
        )

        userPrefs = UserPreferencesDataStore(context = context, dataStore = testDataStore)
    }

    @Test
    fun defaultValues_areCorrect() = runBlocking {
        val theme = userPrefs.themeMode.first()
        val language = userPrefs.language.first()

        assertEquals(ThemePreference.SYSTEM, theme)
        assertEquals(AppLanguage.ENGLISH, language)
    }

    @Test
    fun setThemeMode_savesValueCorrectly() = runBlocking {
        userPrefs.setThemeMode(ThemePreference.DARK)
        val theme = userPrefs.themeMode.first()
        assertEquals(ThemePreference.DARK, theme)
    }

    @Test
    fun setLanguage_savesValueCorrectly() = runBlocking {
        userPrefs.setLanguage(AppLanguage.TURKISH)
        val language = userPrefs.language.first()
        assertEquals(AppLanguage.TURKISH, language)
    }
}
