package com.wamcdevs.habitsappmvvm.core.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.wamcdevs.habitsapp.core.util.UiEvent
import com.wamcdevs.habitsappmvvm.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitsTopBar(
    modifier: Modifier = Modifier,
    title: String = "",
    onNavigate: () -> Unit = {},
    isNavigate: Boolean = false,
    containerColor: Color = MaterialTheme.colorScheme.primary
) {

    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        navigationIcon = {
            if (isNavigate) {
                IconButton(onClick = { onNavigate() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(
                            id = R.string.description_navigation_icon
                        )
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = containerColor)
    )
}

@Preview()
@Composable
fun HabitsTopBarPreview() {
    HabitsTopBar(onNavigate = { /*TODO*/ })
}