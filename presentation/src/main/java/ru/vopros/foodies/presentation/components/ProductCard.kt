package ru.vopros.foodies.presentation.components

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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vopros.foodies.domain.model.Product
import ru.vopros.foodies.presentation.resources
import ru.vopros.foodies.presentation.theme.Grey
import ru.vopros.foodies.presentation.theme.LightGrey

@Composable
fun ProductCard(
    product: Product,
    count: Int = 0,
    onClick: () -> Unit,
    onAddClick: () -> Unit,
    onRemoveClick: () -> Unit,
) {
    val secondImgs = fetchSecondImage(product)
    val buttonText = remember {
        mutableStateOf(buildAnnotatedString {
            withStyle(SpanStyle(fontSize = 12.sp)) {
                append(product.priceCurrent)
            }
            append(" ")
            product.priceOld?.let {
                withStyle(
                    SpanStyle(
                        color = Grey,
                        fontSize = 10.sp,
                        textDecoration = TextDecoration.LineThrough
                    )
                ) {
                    append(it)
                }
            }
        })
    }

    Card(
        elevation = 0.dp,
        backgroundColor = LightGrey,
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick() }
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(
                contentAlignment = Alignment.TopStart,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(resources[product.image]!!),
                    contentDescription = product.name,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                if (secondImgs.isNotEmpty()) {
                    Row (horizontalArrangement = Arrangement.spacedBy(1.dp)) {
                        secondImgs.map {
                            Image(
                                painter = painterResource(resources[it]!!),
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
                    style = MaterialTheme.typography.body2,
                )
                Text(
                    text = "${product.measure} ${product.measureUnit}",
                    color = Grey,
                    style = MaterialTheme.typography.body2
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
            ) {
                when (count != 0) {
                    true -> {
                        AddRemoveButtonsRow(
                            count = count,
                            color = Color.White,
                            modifier = Modifier.fillMaxWidth(),
                            arrangement = Arrangement.SpaceBetween,
                            onRemoveClick = onRemoveClick,
                            onAddClick = onAddClick
                        )
                    }
                    else -> {
                        Button(
                            onClick = { onAddClick() },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.White
                            ),
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