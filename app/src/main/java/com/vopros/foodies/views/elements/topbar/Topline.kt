package com.vopros.foodies.views.elements.topbar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vopros.foodies.R
import com.vopros.foodies.animations.CrossSlide
import com.vopros.foodies.ui.theme.Grey
import com.vopros.foodies.ui.theme.Primary
import com.vopros.foodies.ui.theme.Typography
import com.vopros.foodies.views.filters.FiltersViewModel

@Composable
fun Topline(
    action: TopBarAction,
    searchText: MutableState<String>,
    onFilterClick: () -> Unit,
    onChangeState: (Boolean) -> Unit,
    onBasketClose: () -> Unit
) {
    CrossSlide(action.toplineState.value) { state ->
        when (state) {
            ToplineState.SEARCH -> OpenedAppBar(
                searchText = searchText.value,
                onCloseClicked = {
                    onChangeState(true)
                    action.updateToplineState(ToplineState.DEFAULT)
                },
                onSearchTextChange = { searchText.value = it }
            )
            ToplineState.DEFAULT -> ClosedAppBar(
                onFilterClick = onFilterClick,
                isFiltersApply = action.isFiltersApply,
                onSearchClick = {
                    onChangeState(false)
                    action.updateToplineState(ToplineState.SEARCH)
                }
            )
            ToplineState.BASKET -> BasketTopline(onBasketClose)
        }
    }
}

@Composable
fun ClosedAppBar(
    isFiltersApply: MutableState<Boolean>,
    onFilterClick: () -> Unit,
    onSearchClick: () -> Unit,
    viewModel: FiltersViewModel = hiltViewModel()
) {
    val filters by viewModel.filter.observeAsState(emptyList())
    DefaultTopAppBar (elevation = 0.dp) {
        IconButton(onClick = onFilterClick) {
            BadgedBox(badge = {
                if (filters.isNotEmpty()) {
                    Badge(backgroundColor = Primary) {
                        Text("${filters.size}")
                    }
                }
            }) {
                Icon(
                    painterResource(R.drawable.filter),
                    contentDescription = "filter"
                )
            }
        }
        Spacer(Modifier.weight(1f, true))
        Icon(
            painterResource(R.drawable.logo),
            contentDescription = "logo",
            tint = Primary
        )
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = onSearchClick) {
            Icon(
                painterResource(R.drawable.search),
                contentDescription = "search"
            )
        }
    }
    LaunchedEffect(isFiltersApply.value) {
        viewModel.fetchFilters()
    }
}

@Composable
fun OpenedAppBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    DefaultTopAppBar {
        IconButton(onClick = onCloseClicked) {
            Icon(
                painter = painterResource(id = R.drawable.left_arrow),
                contentDescription = "back",
                tint = Primary
            )
        }
        TextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(stringResource(R.string.find_product), style = Typography.body1) },
            singleLine = true,
            textStyle = Typography.body1,
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            )
        )
        if (searchText.isNotEmpty()) {
            IconButton(onClick = { onSearchTextChange("") }) {
                Icon(
                    painterResource(R.drawable.cancel),
                    contentDescription = "clear",
                    tint = Grey
                )
            }
        }
    }
}

@Composable
fun BasketTopline(onCloseClicked: () -> Unit) {
    DefaultTopAppBar {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onCloseClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.left_arrow),
                    contentDescription = "back",
                    tint = Primary
                )
            }
            Text(stringResource(R.string.basket))
        }
    }
}

@Composable
fun DefaultTopAppBar(
    elevation: Dp = 4.dp,
    content: @Composable RowScope.() -> Unit
) {
    TopAppBar(
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 16.dp),
        backgroundColor = Color.White,
        elevation = elevation,
        content = content
    )
}