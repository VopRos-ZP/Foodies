package ru.vopros.foodies.presentation.screens.root

import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.vopros.foodies.presentation.components.LockScreenOrientation
import ru.vopros.foodies.presentation.screens.Main
import ru.vopros.foodies.presentation.screens.Splash
import ru.vopros.foodies.presentation.screens.main.MainScreen
import ru.vopros.foodies.presentation.screens.splash.SplashScreen

@Composable
fun RootScreen() {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Splash
    ) {
        composable<Splash> {
            SplashScreen(
                onLoaded = {
                    navController.navigate(Main) {
                        popUpTo(Splash) { inclusive = true }
                    }
                }
            )
        }
        composable<Main> {
            MainScreen()
        }
    }
}