package ru.vopros.foodies.presentation.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import ru.vopros.foodies.presentation.theme.Primary

const val SPLASH_SCREEN_ASSET_NAME = "SplashScreenAnimation.json"

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = koinViewModel(),
    onLoaded: () -> Unit,
) {
    val state by viewModel.collectAsState()
    viewModel.collectSideEffect {
        when (it) {
            is SplashSideEffect.OnLoaded -> onLoaded()
        }
    }

    val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset(SPLASH_SCREEN_ASSET_NAME)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = state.isPlaying,
        restartOnPlay = state.restartOnPlay
    )
    LottieAnimation(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary),
        composition = composition,
        progress = { progress }
    )
    LaunchedEffect(progress) {
        viewModel.onProgressChange(progress)
    }
}