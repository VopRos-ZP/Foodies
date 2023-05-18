package com.vopros.foodies.animations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vopros.foodies.ui.theme.Primary

@Composable
fun StartAnimation(show: MutableState<Boolean>) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("SplashScreenAnimation.json"))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = true,
        restartOnPlay = false
    )
    Box(
        modifier = Modifier.fillMaxSize().background(Primary)
    ) {
        LottieAnimation(composition, progress = { progress })
    }
    LaunchedEffect(progress) {
        if (progress >= 0.7f) {
            show.value = false
        }
    }
}