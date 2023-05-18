package com.vopros.foodies.views.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vopros.foodies.R
import com.vopros.foodies.domain.product.Product
import com.vopros.foodies.ui.theme.Primary
import com.vopros.foodies.ui.theme.Typography
import com.vopros.foodies.views.catalog.CatalogViewModel

@Composable
fun AddRemoveButtonsRow(
    product: Product,
    count: Int,
    color: Color,
    modifier: Modifier = Modifier,
    arrangement: Arrangement.Horizontal = Arrangement.spacedBy(10.dp),
    viewModel: CatalogViewModel = hiltViewModel()
) {
    Row(
        modifier = modifier,
        horizontalArrangement = arrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { viewModel.removeProduct(product) },
            modifier = Modifier.background(color, RoundedCornerShape(8.dp))
        ) {
            Icon(
                painter = painterResource(R.drawable.minus),
                contentDescription = "minus",
                tint = Primary
            )
        }
        Text(
            text = "$count",
            style = Typography.button
        )
        IconButton(
            modifier = Modifier.background(color, RoundedCornerShape(8.dp)),
            onClick = { viewModel.addProduct(product) }
        ) {
            Icon(
                painter = painterResource(R.drawable.plus),
                contentDescription = "plus",
                tint = Primary
            )
        }
    }
}