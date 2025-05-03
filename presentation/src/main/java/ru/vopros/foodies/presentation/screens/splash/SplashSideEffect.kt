package ru.vopros.foodies.presentation.screens.splash

sealed interface SplashSideEffect {
    data object OnLoaded : SplashSideEffect
}