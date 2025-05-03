package ru.vopros.foodies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.koin.androidx.compose.KoinAndroidContext
import ru.vopros.foodies.presentation.screens.root.RootScreen
import ru.vopros.foodies.presentation.theme.FoodiesTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinAndroidContext {
                FoodiesTheme {
                    RootScreen()
                }
            }
        }
    }

}