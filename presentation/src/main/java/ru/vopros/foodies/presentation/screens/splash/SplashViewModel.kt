package ru.vopros.foodies.presentation.screens.splash

import ru.vopros.foodies.presentation.orbit.ContainerViewModel

class SplashViewModel : ContainerViewModel<SplashViewState, SplashSideEffect>(
    initialState = SplashViewState()
) {

    fun onProgressChange(value: Float) = intent {
        if (value == 1f) {
            postSideEffect(SplashSideEffect.OnLoaded)
        }
    }

}