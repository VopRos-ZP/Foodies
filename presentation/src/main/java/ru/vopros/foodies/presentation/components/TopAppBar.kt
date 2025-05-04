package ru.vopros.foodies.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vopros.foodies.presentation.R

@Composable
fun DefaultTopBar(
    onFilterClick: () -> Unit,
    onSearchClick: () -> Unit,
) {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = Color.White,
        contentPadding = PaddingValues(
            top = 16.dp, start = 8.dp, end = 8.dp, bottom = 8.dp
        )
    ) {
        IconButton(onClick = onFilterClick) {
            Icon(
                painter = painterResource(R.drawable.filter),
                contentDescription = "filter",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .weight(1f)
                .height(44.dp)
        )
        IconButton(onClick = onSearchClick) {
            Icon(
                painter = painterResource(R.drawable.search),
                contentDescription = "search",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun BackTopBar(
    onBackClick: () -> Unit,
    @StringRes title: Int,
) {
    TopAppBar(
        backgroundColor = Color.White,
        contentColor = Color.Black,
        elevation = 16.dp,
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painterResource(R.drawable.left_arrow),
                    contentDescription = "left_arrow",
                    tint = MaterialTheme.colors.primary
                )
            }
        },
        title = {
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.subtitle1.copy(
                    fontSize = 18.sp
                ),
            )
        },
    )
}