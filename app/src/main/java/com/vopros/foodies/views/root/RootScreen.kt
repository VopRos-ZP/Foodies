package com.vopros.foodies.views.root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vopros.foodies.domain.product.Product
import com.vopros.foodies.ui.theme.Grey
import com.vopros.foodies.views.basket.BasketScreen
import com.vopros.foodies.views.catalog.CatalogScreen
import com.vopros.foodies.views.catalog.SearchScreen
import com.vopros.foodies.views.elements.bottombar.BottomButtonBar
import com.vopros.foodies.views.elements.topbar.Topline
import com.vopros.foodies.views.elements.bottombar.BottomBarAction
import com.vopros.foodies.views.elements.bottombar.localBBA
import com.vopros.foodies.views.elements.topbar.TopBarAction
import com.vopros.foodies.views.elements.topbar.localTBA
import com.vopros.foodies.views.filters.FiltersContent
import com.vopros.foodies.views.product_info.ProductInfoScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RootScreen() {
    val navController = rememberNavController()
    val showTopline = remember { mutableStateOf(true) }
    val showCategories = remember { mutableStateOf(true) }
    val searchText = remember { mutableStateOf("") }
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false
    )
    val scope = rememberCoroutineScope()
    val bottomBarAction = BottomBarAction()
    val toplineBarAction = TopBarAction()

    CompositionLocalProvider(
        localBBA provides bottomBarAction,
        localTBA provides toplineBarAction
    ) {
        ModalBottomSheetLayout(
            sheetContent = { FiltersContent(
                onCloseSheet = {
                    toplineBarAction.applyFilters()
                    scope.launch { modalSheetState.hide() }
                }
            ) },
            sheetState = modalSheetState,
            sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ) {
            Scaffold (
                topBar = {
                    if (showTopline.value) {
                        Topline(
                            action = toplineBarAction,
                            searchText = searchText,
                            onFilterClick = { scope.launch { modalSheetState.show() } },
                            onChangeState = { showCategories.value = it },
                            onBasketClose = {
                                showTopline.value = true
                                showCategories.value = true
                                navController.navigateUp()
                            }
                        )
                    }
                },
                bottomBar = { BottomButtonBar() }
            ) { padding ->
                NavHost(
                    modifier = Modifier.padding(padding),
                    navController = navController,
                    startDestination = "root"
                ) {
                    composable(route = "root") {
                        val onProductClick: (Product) -> Unit = {
                            showTopline.value = false
                            showCategories.value = false
                            navController.navigate("product/${it.id}") {
                                popUpTo("root")
                                launchSingleTop = true
                            }
                        }
                        if (!showCategories.value) {
                            SearchScreen(
                                searchText = searchText,
                                onProductClick = onProductClick
                            )
                        } else {
                            CatalogScreen(
                                viewModel = hiltViewModel(),
                                showCategories = showCategories,
                                isFiltersApply = toplineBarAction.isFiltersApply,
                                onProductClick = onProductClick,
                                onBasketShowClick = {
                                    navController.navigate("basket") {
                                        popUpTo("root")
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }
                    }
                    composable(route = "basket") { BasketScreen() }
                    composable(route = "product/{id}",
                        arguments = listOf(navArgument("id") { type = NavType.IntType })
                    ) {
                        val productId = it.arguments!!.getInt("id")
                        ProductInfoScreen(
                            viewModel = hiltViewModel(),
                            productId = productId,
                            onBackClick = {
                                showTopline.value = true
                                showCategories.value = true
                                navController.navigateUp()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorScreen(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            color = Grey,
            textAlign = TextAlign.Center
        )
    }
}
