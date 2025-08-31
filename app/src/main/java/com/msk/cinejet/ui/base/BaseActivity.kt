package com.msk.cinejet.ui.base

import android.content.Context
import androidx.activity.ComponentActivity
import com.msk.cinejet.ui.LocaleHelper
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

abstract class BaseActivity : ComponentActivity() {



    override fun attachBaseContext(newBase: Context) {
        val userPreferencesDataStore = EntryPointAccessors.fromApplication(
            newBase.applicationContext,
            UserPreferencesEntryPoint::class.java
        ).userPreferencesDataStore()

        val lang = runBlocking {
            userPreferencesDataStore.language.first().code
        }

        val context = LocaleHelper.setLocale(newBase, lang)
        super.attachBaseContext(context)
    }

}
