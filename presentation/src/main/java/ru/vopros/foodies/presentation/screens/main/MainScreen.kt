package ru.vopros.foodies.presentation.screens.main

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ru.vopros.foodies.presentation.screens.Basket
import ru.vopros.foodies.presentation.screens.Catalog
import ru.vopros.foodies.presentation.screens.Details
import ru.vopros.foodies.presentation.screens.basket.BasketScreen
import ru.vopros.foodies.presentation.screens.catalog.CatalogScreen
import ru.vopros.foodies.presentation.screens.details.ProductDetailsScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier.statusBarsPadding(),
        navController = navController,
        startDestination = Catalog
    ) {
        composable<Catalog> {
            CatalogScreen(
                onProductClick = {
                    navController.navigate(Details(it))
                },
                onBasketClick = {
                    navController.navigate(Basket)
                }
            )
        }
        composable<Details> {
            ProductDetailsScreen(
                onBackClick = { navController.navigateUp() },
                id = it.toRoute<Details>().id
            )
        }
        composable<Basket> {
            BasketScreen(
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}