package com.vopros.foodies.views.product_info

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vopros.foodies.R
import com.vopros.foodies.domain.product.Product
import com.vopros.foodies.utils.RUB
import com.vopros.foodies.utils.getResource
import com.vopros.foodies.ui.theme.Grey
import com.vopros.foodies.ui.theme.LightGrey
import com.vopros.foodies.ui.theme.Typography
import com.vopros.foodies.views.catalog.CatalogViewModel
import com.vopros.foodies.views.elements.AddRemoveButtonsRow
import com.vopros.foodies.views.elements.ListItem
import com.vopros.foodies.views.elements.bottombar.localBBA
import com.vopros.foodies.views.root.ErrorScreen

@Composable
fun ProductInfoScreen(
    productId: Int,
    onBackClick: () -> Unit,
    viewModel: CatalogViewModel = hiltViewModel()
) {
    val product by viewModel.product.observeAsState()
    when (product) {
        null -> ErrorScreen(stringResource(R.string.not_found))
        else -> ProductInfoScreen(
            product = product!!,
            onBackClick = onBackClick
        )
    }
    LaunchedEffect(null) {
        viewModel.fetchProductById(productId)
    }
}

@Composable
fun ProductInfoScreen(
    product: Product,
    onBackClick: () -> Unit,
    viewModel: CatalogViewModel = hiltViewModel()
) {
    val action = localBBA.current
    val baskets = viewModel.baskets.observeAsState(SnapshotStateList())
    val currentBasket = remember(baskets.value) { mutableStateOf(baskets.value.find { it.product.id == product.id }) }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopStart
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Image(
                    ImageBitmap.imageResource(getResource(product.image)),
                    contentDescription = product.image,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = product.name, style = Typography.h4)
                    Text(text = product.description, style = Typography.body1)
                }
            }
            item {
                Column {
                    Divider(color = Grey)
                    mapOf(
                        R.string.measure to "${product.measure} г",
                        R.string.energy to "${product.energy} ккал",
                        R.string.proteins to "${product.proteins} г",
                        R.string.fats to "${product.fats} г",
                        R.string.carbohydrates to "${product.carbohydrates} г",
                    ).map { (key, value) ->
                        ListItem(stringResource(key), value)
                        Divider(color = Grey)
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = onBackClick,
            shape = CircleShape,
            backgroundColor = Color.White,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        ) {
            Icon(
                painterResource(R.drawable.left_arrow),
                contentDescription = "back"
            )
        }
    }

    LaunchedEffect(null) {
        viewModel.fetchBaskets()
    }
    LaunchedEffect(currentBasket.value) {
        when (val basket = currentBasket.value) {
            null -> action.showBar({ viewModel.addProduct(product) }) {
                Text(text = "В корзину за ${product.priceCurrent} $RUB")
            }
            else -> action.showCustomBar {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AddRemoveButtonsRow(
                        product = basket.product,
                        count = basket.count,
                        color = LightGrey,
                        modifier = Modifier.fillMaxWidth(),
                        arrangement = Arrangement.SpaceAround
                    )
                }
            }
        }
    }
}