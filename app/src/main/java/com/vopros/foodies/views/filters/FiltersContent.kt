package com.vopros.foodies.views.filters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vopros.foodies.R
import com.vopros.foodies.ui.theme.Grey
import com.vopros.foodies.ui.theme.Typography
import com.vopros.foodies.views.elements.TextCheckBox

@Composable
fun FiltersContent(
    onCloseSheet: () -> Unit,
    viewModel: FiltersViewModel = hiltViewModel()
) {
    val tags by viewModel.tags.observeAsState(emptyList())
    val filter by viewModel.filter.observeAsState(mutableListOf())
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = stringResource(R.string.pick_up_product), style = Typography.subtitle1)
        Column {
            tags.mapIndexed { i, tag ->
                val checkState = remember { mutableStateOf(filter.contains(tag)) }
                TextCheckBox(checked = checkState, text = tag.name)
                if (tags.lastIndex != i) {
                    Divider(color = Grey)
                }
                LaunchedEffect(checkState.value) {
                    when (checkState.value) {
                        true -> filter.add(tag)
                        else -> filter.remove(tag)
                    }
                }
            }
        }
        Button(
            onClick = {
                viewModel.applyFilters(filter)
                onCloseSheet()
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text(text = stringResource(R.string.done), color = Color.White) }
    }
    LaunchedEffect(null) {
        viewModel.fetchTags()
        viewModel.fetchFilters()
    }
}
