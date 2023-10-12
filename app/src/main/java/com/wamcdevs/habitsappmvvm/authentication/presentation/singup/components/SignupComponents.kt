package com.wamcdevs.habitsappmvvm.authentication.presentation.singup.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.wamcdevs.habitsappmvvm.R
import com.wamcdevs.habitsappmvvm.authentication.presentation.singup.SignupViewModel
import com.wamcdevs.habitsappmvvm.core.LocalSpacing
import com.wamcdevs.habitsappmvvm.core.presentation.components.HabitsProgressBar

@Composable
fun SignupComponents(paddingValues: PaddingValues, viewModel: SignupViewModel = hiltViewModel()) {

    val space = LocalSpacing.current

    val state = viewModel.state

    Box(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier.aspectRatio(1.5f),
                painter = painterResource(id = R.drawable.signup),
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )

            Spacer(modifier = Modifier.height(space.spaceLarge))


            SignupForm(state = state, onEvent = {
                viewModel.onEvent(it)
            })

        }

        if (state.isLoading) {
            HabitsProgressBar()
        }

        if (state.showDialog) {
            SignupDialog(onDismissRequest = {  }, onEvent = {
                viewModel.onEvent(it)
            })
        }

    }
}