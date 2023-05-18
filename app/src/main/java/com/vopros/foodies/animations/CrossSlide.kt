package com.vopros.foodies.animations

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun <T> CrossSlide(
    targetState: T,
    modifier: Modifier = Modifier,
    animationSpec: FiniteAnimationSpec<Offset> = tween(500),
    reverseAnimation: Boolean = false,
    content: @Composable (T) -> Unit
) {
    val direction: Int = if (reverseAnimation) -1 else 1
    val items = remember { mutableStateListOf<SlideInOutAnimationState<T>>() }
    val transitionState = remember { MutableTransitionState(targetState) }
    val targetChanged = (targetState != transitionState.targetState)
    transitionState.targetState = targetState
    val transition: Transition<T> = updateTransition(transitionState, "")

    if (targetChanged || items.isEmpty()) {
        val keys = items.map { it.key }.run {
            if (!contains(targetState)) {
                toMutableList().also { it.add(targetState) }
            } else {
                this
            }
        }
        items.clear()
        keys.mapTo(items) { key ->
            SlideInOutAnimationState(key) {
                val yTransition by transition.animateOffset(
                    transitionSpec = { animationSpec }, label = ""
                ) { if (it == key) Offset(0f, 0f) else Offset(1000f, -250f) }
                Box(modifier.graphicsLayer {
                    this.translationY =
                        if (transition.targetState == key) direction * yTransition.y
                        else -direction * -yTransition.y
                }) {
                    content(key)
                }
            }
        }
    } else if (transitionState.currentState == transitionState.targetState) {
        items.removeAll { it.key != transitionState.targetState }
    }

    Box(modifier) {
        items.forEach {
            key(it.key) {
                it.content()
            }
        }
    }
}


data class SlideInOutAnimationState<T>(
    val key: T,
    val content: @Composable () -> Unit
)