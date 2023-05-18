package com.vopros.foodies.views.elements.product_card

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vopros.foodies.domain.product.Product
import com.vopros.foodies.ui.theme.Grey
import com.vopros.foodies.ui.theme.LightGrey
import com.vopros.foodies.ui.theme.Typography
import com.vopros.foodies.utils.RUB
import com.vopros.foodies.utils.getResource
import com.vopros.foodies.views.catalog.CatalogViewModel
import com.vopros.foodies.views.elements.AddRemoveButtonsRow

@Composable
fun ProductCard(
    product: Product,
    onProductClick: (Product) -> Unit,
    viewModel: CatalogViewModel = hiltViewModel()
) {
    val baskets = viewModel.baskets.observeAsState(SnapshotStateList())
    val currentBasket = remember(baskets.value) { mutableStateOf(baskets.value.find { it.product.id == product.id }) }
    val secondImgs = fetchSecondImage(product)
    val buttonText = remember {
        mutableStateOf(buildAnnotatedString {
            withStyle(SpanStyle(fontSize = 12.sp, fontWeight = FontWeight.W500)) {
                append("${product.priceCurrent} $RUB")
            }
            if (product.priceOld != null) {
                withStyle(
                    SpanStyle(
                        color = Grey,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.W400,
                        textDecoration = TextDecoration.LineThrough
                    )
                ) {
                    append(" ${product.priceOld} $RUB")
                }
            }
        })
    }

    Card(
        backgroundColor = LightGrey,
        modifier = Modifier.clickable { onProductClick(product) }
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(
                contentAlignment = Alignment.TopStart,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painterResource(getResource(product.image)),
                    contentDescription = product.name,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                if (secondImgs.isNotEmpty()) {
                    Row (horizontalArrangement = Arrangement.spacedBy(1.dp)) {
                        secondImgs.map {
                            Image(
                                painterResource(id = getResource(it)),
                                contentDescription = it,
                                modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                                contentScale = ContentScale.Inside
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = product.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = Typography.body2,
                )
                Text(
                    text = "${product.measure} ${product.measureUnit}",
                    color = Grey,
                    style = Typography.body2
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
            ) {
                when (currentBasket.value != null) {
                    true -> AddRemoveButtonsRow(
                        product = product,
                        count = currentBasket.value?.count ?: 0,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(),
                        arrangement = Arrangement.SpaceBetween
                    )
                    else -> {
                        Button(
                            onClick = { viewModel.addProduct(product) },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = buttonText.value, textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }
    }
}

fun fetchSecondImage(product: Product): List<String> {
    val res = mutableListOf<String>()
    if (product.priceOld != null) {
        res.add("discount")
    }
    if (product.tagIds.contains(2)) {
        res.add("leaf")
    }
    if (product.tagIds.contains(4)) {
        res.add("pepper")
    }
    return res
}