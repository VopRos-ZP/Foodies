package com.vopros.foodies

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.vopros.foodies.animations.HorizontalCrossSlide
import com.vopros.foodies.animations.StartAnimation
import com.vopros.foodies.ui.theme.FoodiesTheme
import com.vopros.foodies.utils.LockScreenOrientation
import com.vopros.foodies.views.root.RootScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodiesTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    val showAnimation = remember { mutableStateOf(true) }
                    HorizontalCrossSlide(targetState = showAnimation.value) {
                        when (it) {
                            true -> StartAnimation(showAnimation)
                            else -> RootScreen()
                        }
                    }
                }
            }
        }
    }
}