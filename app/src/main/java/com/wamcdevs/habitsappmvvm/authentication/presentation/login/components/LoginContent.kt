package com.wamcdevs.habitsappmvvm.authentication.presentation.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.wamcdevs.habitsappmvvm.R
import com.wamcdevs.habitsappmvvm.core.LocalSpacing
import com.wamcdevs.habitsappmvvm.core.presentation.components.HabitTitle

@Composable
fun LoginContent(paddingValues: PaddingValues) {

    val space = LocalSpacing.current

    Box(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .fillMaxSize()
    ) {

        Image(
            modifier = Modifier
                .aspectRatio(1f)
                // Le damos Zoom
                .graphicsLayer(scaleX = 1.7f, scaleY = 1.7f),
            painter = painterResource(id = R.drawable.loginbackground),
            contentDescription = stringResource(
                id = R.string.login_description_background
            ),
            contentScale = ContentScale.Crop
        )

        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush =
                    // Damos el degradado vertical a la imagen con este Spacer iplementando esto
                    Brush.verticalGradient(
                        colors = listOf(
                            // Y el gradiente iniciara en 3 partes de la pantalla
                            Color.Transparent,
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {

            HabitTitle(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = space.spaceMedium),
                text = stringResource(id = R.string.login_title).uppercase()
            )

            Spacer(modifier = Modifier.height(space.spaceExtraLargeMax))

            LoginForm()


        }

    }


}