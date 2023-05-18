package com.vopros.foodies.views.catalog

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vopros.foodies.R
import com.vopros.foodies.domain.product.Product
import com.vopros.foodies.utils.RUB
import com.vopros.foodies.views.categories.CategoryMenu
import com.vopros.foodies.views.categories.CategoryViewModel
import com.vopros.foodies.views.elements.bottombar.localBBA
import com.vopros.foodies.views.elements.product_card.ProductCard
import com.vopros.foodies.views.elements.topbar.ToplineState
import com.vopros.foodies.views.elements.topbar.localTBA
import com.vopros.foodies.views.root.ErrorScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatalogScreen(
    viewModel: CatalogViewModel = hiltViewModel(),
    showCategories: MutableState<Boolean>,
    isFiltersApply: MutableState<Boolean>,
    onProductClick: (Product) -> Unit,
    onBasketShowClick: () -> Unit
) {
    val action = localBBA.current
    val topBarAction = localTBA.current
    val categoryViewModel: CategoryViewModel = hiltViewModel()

    val categories by categoryViewModel.categories.observeAsState(emptyList())
    val products by viewModel.products.observeAsState(emptyList())
    val sum = viewModel.sum.observeAsState(0)

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        if (categories.isNotEmpty()) {
            CategoryMenu(
                showCategories = showCategories.value,
                pagerState = pagerState,
                categories = categories,
                onClick = { scope.launch { pagerState.animateScrollToPage(it) } }
            )
            HorizontalPager(pageCount = categories.size, state = pagerState) { page ->
                val categoryProducts = products.filter { it.categoryId == categories[page].id }
                VisibilityProducts(
                    errorMessage = stringResource(R.string.empty_filters_error),
                    products = categoryProducts,
                    onProductClick = onProductClick
                )
            }
        }
    }
    LaunchedEffect(null) {
        categoryViewModel.fetchCategories()
        viewModel.fetchBaskets()
        topBarAction.updateToplineState(ToplineState.DEFAULT)
    }
    LaunchedEffect(sum.value) {
        when (sum.value) {
            0 -> action.hide()
            else -> {
                action.showBar(onBasketShowClick) {
                    Row (
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.cart),
                            contentDescription = "cart",
                            tint = Color.White
                        )
                        Text(
                            text = "${sum.value} $RUB",
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
    LaunchedEffect(isFiltersApply.value) {
        viewModel.fetchProducts()
    }
}

@Composable
fun VisibilityProducts(
    errorMessage: String,
    products: List<Product>,
    onProductClick: (Product) -> Unit
) {
    when (products.isEmpty()) {
        true -> ErrorScreen(errorMessage)
        else -> {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(products) { p ->
                    ProductCard(p, onProductClick)
                }
            }
        }
    }
}

@Composable
fun SearchScreen(
    searchText: MutableState<String>,
    onProductClick: (Product) -> Unit
) {
    val action = localBBA.current
    val viewModel: CatalogViewModel = hiltViewModel()
    val foundedProducts by viewModel.products.observeAsState(emptyList())

    if (searchText.value.isEmpty()) {
        ErrorScreen(stringResource(R.string.enter_product_name_in_search))
    } else {
        VisibilityProducts(
            errorMessage = stringResource(R.string.not_found),
            products = foundedProducts,
            onProductClick = onProductClick
        )
    }
    LaunchedEffect(null) {
        action.hide()
    }
    LaunchedEffect(searchText.value) {
        viewModel.findProducts(searchText.value)
    }
}
