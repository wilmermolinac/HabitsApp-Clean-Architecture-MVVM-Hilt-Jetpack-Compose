package com.wamcdevs.habitsappmvvm.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationHost(navHostController: NavHostController, startNavigationRoute: NavigationRoute) {

    NavHost(
        navController = navHostController,
        startDestination = startNavigationRoute.route,
        builder = {

            composable(route = NavigationRoute.OnBoarding.route) {
                Text(text = "OnBoarding Screen")
            }


        })
}