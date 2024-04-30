package com.betuel.translatorapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.betuel.translatorapp.android.core.presentation.Routes
import com.betuel.translatorapp.android.translate.presentation.AndroidTranslateViewModel
import com.betuel.translatorapp.android.translate.presentation.TranslateScreen
import com.betuel.translatorapp.android.voice_to_text.presentation.AndroidVoiceToTextViewModel
import com.betuel.translatorapp.android.voice_to_text.presentation.VoiceToTextScreen
import com.betuel.translatorapp.translate.presentation.TranslateEvent
import com.betuel.translatorapp.voice_to_text.presentation.VoiceToTextEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TranslatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TranslateRoot()
                }
            }
        }
    }
}

@Composable
fun TranslateRoot() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.TRANSLATE
    ) {
        composable(route = Routes.TRANSLATE) {
            val viewmodel = hiltViewModel<AndroidTranslateViewModel>()
            val state by viewmodel.state.collectAsState()

            val voiceResult by it.savedStateHandle.getStateFlow<String?>("voiceResult", null)
                .collectAsState()

            LaunchedEffect(voiceResult) {
                viewmodel.onEvent(TranslateEvent.SubmitVoiceResult(voiceResult))
                it.savedStateHandle["voiceResult"] = null
            }

            TranslateScreen(
                state = state,
                onEvent = { event ->
                    when (event) {
                        is TranslateEvent.RecordAudio -> {
                            navController.navigate(Routes.VOICE_TO_TEXT + "/${state.fromLanguage.language.langCode}")
                        }

                        else -> viewmodel.onEvent(event)
                    }
                }
            )
        }
        composable(route = Routes.VOICE_TO_TEXT + "/{languageCode}", arguments = listOf(
            navArgument("languageCode") {
                type = NavType.StringType
                defaultValue = "en"
            }
        )) { backStackEntry ->
            val languageCode = backStackEntry.arguments?.getString("languageCode") ?: "en"
            val viewModel = hiltViewModel<AndroidVoiceToTextViewModel>()
            val state by viewModel.state.collectAsState()

            VoiceToTextScreen(
                state = state,
                languageCode = languageCode,
                onResult = { spokenText ->
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "voiceResult",
                        spokenText
                    )
                    navController.popBackStack()
                },
                onEvent = { event ->
                    when (event) {
                        is VoiceToTextEvent.Close -> {
                            navController.popBackStack()
                        }

                        else -> viewModel.onEvent(event)
                    }
                })
        }
    }
}

