package ru.vopros.foodies.presentation.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import ru.vopros.foodies.presentation.R
import ru.vopros.foodies.presentation.components.ListItem
import ru.vopros.foodies.presentation.resources
import ru.vopros.foodies.presentation.theme.Grey

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    id: Int,
) {
    val state by viewModel.collectAsState()
    viewModel.collectSideEffect {
        when (it) {
            is ProductDetailsSideEffect.OnBackClick -> onBackClick()
        }
    }

    Scaffold(
        bottomBar = {
            state.product?.let {
                BottomAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = Color.White,
                    elevation = 8.dp,
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {},
                        contentPadding = PaddingValues(vertical = 16.dp)
                    ) {
                        Text(
                            text = "В корзину за ${it.priceCurrent}"
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopStart
        ) {
            if (state.product != null) {
                val product = state.product!!
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Image(
                        painter = painterResource(resources[product.image]!!),
                        contentDescription = product.image,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = product.name,
                            style = MaterialTheme.typography.h4
                        )
                        Text(
                            text = product.description,
                            color = Grey,
                            style = MaterialTheme.typography.body1
                        )
                    }
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
                onClick = { viewModel.onBackClick() },
                shape = CircleShape,
                backgroundColor = Color.White,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.left_arrow),
                    contentDescription = "left_arrow",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
    LaunchedEffect(id) {
        viewModel.fetchProductById(id)
    }
}