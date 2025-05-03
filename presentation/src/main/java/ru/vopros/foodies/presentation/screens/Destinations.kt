package ru.vopros.foodies.presentation.screens

import kotlinx.serialization.Serializable

@Serializable
data object Splash

@Serializable
data object Main

@Serializable
data object Catalog

@Serializable
data class Details(val id: Int)

@Serializable
data object Basket