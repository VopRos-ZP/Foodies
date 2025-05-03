package ru.vopros.foodies.presentation.orbit

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.Syntax
import org.orbitmvi.orbit.viewmodel.container

abstract class ContainerViewModel<STATE : Any, SIDE_EFFECT : Any>(
    initialState: STATE,
) : ViewModel(), ContainerHost<STATE, SIDE_EFFECT> {

    open suspend fun Syntax<STATE, SIDE_EFFECT>.onCreate() {}

    override val container = container(
        initialState = initialState,
        onCreate = { onCreate() },
    )

}