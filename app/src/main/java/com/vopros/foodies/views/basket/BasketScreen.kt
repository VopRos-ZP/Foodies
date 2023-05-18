package com.vopros.foodies.views.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vopros.foodies.R
import com.vopros.foodies.domain.basket.Basket
import com.vopros.foodies.ui.theme.LightGrey
import com.vopros.foodies.ui.theme.Typography
import com.vopros.foodies.utils.RUB
import com.vopros.foodies.utils.getResource
import com.vopros.foodies.views.catalog.CatalogViewModel
import com.vopros.foodies.views.elements.AddRemoveButtonsRow
import com.vopros.foodies.views.elements.bottombar.localBBA
import com.vopros.foodies.views.elements.topbar.ToplineState
import com.vopros.foodies.views.elements.topbar.localTBA
import com.vopros.foodies.views.root.ErrorScreen

@Composable
fun BasketScreen(viewModel: CatalogViewModel = hiltViewModel()) {
     val action = localBBA.current
     val topBarAction = localTBA.current

     val baskets by viewModel.baskets.observeAsState(SnapshotStateList())
     val sum = viewModel.sum.observeAsState(0)

     Column {
          when (baskets.isEmpty()) {
               true -> ErrorScreen(stringResource(R.string.empty_catalog))
               else -> BasketList(baskets)
          }
     }
     LaunchedEffect(null) {
          topBarAction.updateToplineState(ToplineState.BASKET)
          viewModel.fetchBaskets()
     }
     LaunchedEffect(sum.value) {
          if (baskets.isNotEmpty()) {
               viewModel.fetchBasketSum()
               action.showBar({/* Нажатие на `заказать` */}) {
                    Text(text = "Заказать за ${sum.value} $RUB")
               }
          } else {
               action.hide()
          }
     }
}

@Composable
fun BasketList(products: SnapshotStateList<Basket>) {
     LazyColumn {
          items(products) {
               BasketProductCard(it)
               Divider(color = LightGrey)
          }
     }
}

@Composable
fun BasketProductCard(basket: Basket) {
     val (product, count) = basket
     Card(
          modifier = Modifier
               .fillMaxWidth()
               .padding(16.dp),
          elevation = 0.dp,
          shape = RectangleShape
     ) {
          Row(
               modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
               horizontalArrangement = Arrangement.spacedBy(16.dp)
          ) {
               Image(
                    painter = painterResource(id = getResource(product.image)),
                    contentDescription = product.image,
               )
               Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
               ) {
                    Text(text = product.name, style = Typography.body2)
                    Row(
                         modifier = Modifier.fillMaxWidth(),
                         horizontalArrangement = Arrangement.SpaceBetween,
                         verticalAlignment = Alignment.CenterVertically
                    ) {
                         AddRemoveButtonsRow(
                              product = product,
                              count = count,
                              color = LightGrey
                         )
                         Spacer(Modifier.weight(1f, true))
                         Text(text = buildAnnotatedString {
                              append("${product.priceCurrent} $RUB")
                              if (product.priceOld != null) {
                                   withStyle(SpanStyle(
                                        fontSize = 12.sp,
                                        textDecoration = TextDecoration.LineThrough
                                   )) {
                                        append("\n${product.priceOld} $RUB")
                                   }
                              }
                         }, fontSize = 14.sp, textAlign = TextAlign.Center)
                    }
               }
          }
     }
}