package ru.vopros.foodies.presentation.screens.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import ru.vopros.foodies.domain.RUB
import ru.vopros.foodies.domain.model.Product
import ru.vopros.foodies.presentation.R
import ru.vopros.foodies.presentation.components.AddRemoveButtonsRow
import ru.vopros.foodies.presentation.components.BackTopBar
import ru.vopros.foodies.presentation.components.BottomBarButton
import ru.vopros.foodies.presentation.resources
import ru.vopros.foodies.presentation.theme.LightGrey
import ru.vopros.foodies.presentation.theme.OnSurface
import ru.vopros.foodies.presentation.theme.Outline

@Composable
fun BasketScreen(
    viewModel: BasketViewModel = koinViewModel(),
    onBackClick: () -> Unit,
) {
    val state by viewModel.collectAsState()
    viewModel.collectSideEffect {
        when (it) {
            is BasketSideEffect.OnBackClick -> onBackClick()
        }
    }

    Scaffold(
        topBar = {
            BackTopBar(
                onBackClick = { viewModel.onBackClick() },
                title = R.string.basket
            )
        },
        bottomBar = {
            if (state.sum != 0) {
                BottomBarButton(
                    text = "Заказать за ${state.sum} $RUB",
                    onClick = {}
                )
            }
        }
    ) { innerPadding ->
        if (state.productsCount.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.empty_catalog),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1,
                    color = OnSurface
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                items(
                    items = state.productsCount,
                    key = { it.first.id }
                ) { (product, count) ->
                    BasketCard(
                        product = product,
                        count = count,
                        onAddClick = { viewModel.addProduct(product) },
                        onRemoveClick = { viewModel.removeProduct(product) }
                    )
                }
            }
        }
    }
}

@Composable
fun BasketCard(
    product: Product,
    count: Int,
    onAddClick: () -> Unit,
    onRemoveClick: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painterResource(resources[product.image]!!),
                contentDescription = product.image,
                modifier = Modifier.size(96.dp)
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    AddRemoveButtonsRow(
                        count = count,
                        color = LightGrey,
                        onAddClick = onAddClick,
                        onRemoveClick = onRemoveClick
                    )
                    if (product.priceOld == null) {
                        Box(
                            modifier = Modifier.fillMaxWidth().height(48.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Text(
                                text = product.priceCurrent,
                                style = MaterialTheme.typography.body1.copy(
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    } else {
                        Column(
                            modifier = Modifier.fillMaxWidth().height(48.dp),
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.aligned(Alignment.CenterVertically)
                        ) {
                            Text(
                                text = product.priceCurrent,
                                style = MaterialTheme.typography.body1.copy(
                                    fontWeight = FontWeight.Medium
                                )
                            )
                            Text(
                                text = product.priceOld!!,
                                textDecoration = TextDecoration.LineThrough,
                                style = MaterialTheme.typography.body2.copy(
                                    color = OnSurface
                                )
                            )
                        }
                    }
                }
            }
        }
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Outline
        )
    }
}