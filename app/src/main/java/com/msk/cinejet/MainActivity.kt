package com.msk.cinejet

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.msk.cinejet.ui.CineJetMain
import com.msk.cinejet.ui.LocalAppRestart
import com.msk.cinejet.ui.MainViewModel
import com.msk.cinejet.ui.MyAppRoot
import com.msk.cinejet.ui.base.BaseActivity
import com.msk.design_system.theme.CinejetTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()


        setContent {
            MyAppRoot {
                val viewModel: MainViewModel = hiltViewModel()
                val appTheme by viewModel.themePreference.collectAsState()
                val appLanguage = viewModel.appLanguage
                val restartApp = LocalAppRestart.current


                LaunchedEffect(appLanguage) {
                    appLanguage.onEach {
                    }.collect {

                        restartApp()
                    }
                }
                CinejetTheme(themePreference =appTheme ) {
                    CineJetMain()
                }

            }
        }

    }
}






