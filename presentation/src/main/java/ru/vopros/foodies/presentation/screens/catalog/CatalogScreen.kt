package ru.vopros.foodies.presentation.screens.catalog

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import ru.vopros.foodies.domain.RUB
import ru.vopros.foodies.presentation.R
import ru.vopros.foodies.presentation.components.BottomBarButton
import ru.vopros.foodies.presentation.components.DefaultTopBar
import ru.vopros.foodies.presentation.components.ProductCard
import ru.vopros.foodies.presentation.components.ScrollableCategoryTabRow

@Composable
fun CatalogScreen(
    viewModel: CatalogViewModel = koinViewModel(),
    onProductClick: (Int) -> Unit,
    onBasketClick: () -> Unit,
) {
    val state by viewModel.collectAsState()

    LaunchedEffect(state.sum) {
        Log.d("CatalogScreen", "sum -> ${state.sum}")
    }

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false
    )
    val pagerState = rememberPagerState { state.categories.size }
    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        scrimColor = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
        sheetContent = {},
    ) {
        Scaffold(
            topBar = {
                DefaultTopBar(
                    onFilterClick = { viewModel.onFilterClick() },
                    onSearchClick = {}
                )
            },
            bottomBar = {
                if (state.sum != 0) {
                    BottomBarButton(
                        text = "${state.sum} $RUB",
                        onClick = { viewModel.onBasketClick() },
                        leadingIcon = R.drawable.cart
                    )
                }
            }
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                if (state.categories.isNotEmpty()) {
                    ScrollableCategoryTabRow(
                        selectedTabIndex = pagerState.currentPage,
                        categories = state.categories,
                        onTabClick = { viewModel.onCategoryTabClick(it) }
                    )
                }
                HorizontalPager(
                    state = pagerState,
                    key = { state.categories[it].id }
                ) { page ->
                    val categoryProducts = state.products.filter { it.categoryId == state.categories[page].id }
                    if (categoryProducts.isEmpty()) {
                        EmptyProductsCatalog()
                    } else {
                        LazyVerticalGrid(
                            modifier = Modifier.fillMaxSize(),
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            items(
                                items = categoryProducts,
                                key = { it.id }
                            ) { product ->
                                ProductCard(
                                    product = product,
                                    count = state.baskets[product.id] ?: 0,
                                    onClick = { viewModel.onProductClick(product) },
                                    onAddClick = { viewModel.onAddProductToBasket(product) },
                                    onRemoveClick = { viewModel.onRemoveProductFromBasket(product) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    viewModel.collectSideEffect {
        when (it) {
            is CatalogSideEffect.OnShowBottomSheet -> sheetState.show()
            is CatalogSideEffect.OnHideBottomSheet -> sheetState.hide()
            is CatalogSideEffect.OnTabClick -> pagerState.animateScrollToPage(it.value)
            is CatalogSideEffect.OnProductClick -> onProductClick(it.value)
            is CatalogSideEffect.OnBasketClick -> onBasketClick()
        }
    }
}

@Composable
private fun EmptyProductsCatalog() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.empty_filters_error),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1,
            color = Color.Black.copy(alpha = 0.6f)
        )
    }
}