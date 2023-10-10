package com.wamcdevs.habitsappmvvm.onboarding.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.wamcdevs.habitsappmvvm.R
import com.wamcdevs.habitsappmvvm.core.LocalSpacing
import com.wamcdevs.habitsappmvvm.core.presentation.components.HabitButton
import com.wamcdevs.habitsappmvvm.core.presentation.components.HabitTitle
import com.wamcdevs.habitsappmvvm.onboarding.presentation.OnboardingPagerInfo
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPager(
    modifier: Modifier = Modifier, pages: List<OnboardingPagerInfo>, onFinish: () -> Unit
) {

    val space = LocalSpacing.current

    val pagerState = rememberPagerState {
        pages.size
    }

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier.fillMaxSize()

    ) {

        HorizontalPager(
            modifier = Modifier.align(Alignment.TopCenter), state = pagerState
        ) { pageIndex: Int ->

            val pageInfo = pages.get(pageIndex)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(space.spaceMiddleLarge),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(space.spaceLarge))

                HabitTitle(
                    modifier = Modifier.fillMaxWidth(),
                    text = pageInfo.title.uppercase()
                )

                Spacer(modifier = Modifier.height(space.spaceMiddleLarge))

                Image(
                    modifier = Modifier.aspectRatio(1f),
                    contentScale = ContentScale.FillHeight,
                    painter = painterResource(id = pageInfo.image),
                    contentDescription = pageInfo.title
                )

                Spacer(modifier = Modifier.height(space.spaceMiddleLarge))

                Text(
                    text = pageInfo.subtitle.uppercase(),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.tertiary
                    ),
                    textAlign = TextAlign.Center
                )


            }


        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(
                    start = space.spaceMiddleLarge,
                    end = space.spaceMiddleLarge,
                    bottom = space.spaceMiddleLarge
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            if (pagerState.currentPage == pages.lastIndex) {

                HabitButton(modifier = Modifier.fillMaxWidth(), text = stringResource(id = R.string.button_get_started), onClick = {
                    onFinish()
                })

            } else {

                TextButton(onClick = { onFinish() }) {
                    Text(text = stringResource(id = R.string.skip))
                }

                HorizontalPagerIndicator(pagerState = pagerState, pageCount = pagerState.pageCount)


                TextButton(onClick = {
                    coroutineScope.launch {
                        // Para poder lanzar esta animacion lo hacemos dentro de coroutineScope
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }) {
                    Text(text = stringResource(id = R.string.next))
                }

            }


        }
    }

}