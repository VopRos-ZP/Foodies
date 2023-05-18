package com.vopros.foodies.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import com.vopros.foodies.R

const val RUB = "₽"

private val resources = mutableMapOf(
    "logo" to R.drawable.logo,
    "1.jpg" to R.drawable.product,
    "discount" to R.drawable.discount,
    "pepper" to R.drawable.pepper,
    "leaf" to R.drawable.leaf,
)

fun getResource(key: String): Int = resources[key]!!

@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            activity.requestedOrientation = originalOrientation
        }
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}