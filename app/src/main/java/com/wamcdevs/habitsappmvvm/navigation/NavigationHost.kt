package com.wamcdevs.habitsappmvvm.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wamcdevs.habitsappmvvm.onboarding.presentation.OnboardingScreen

@Composable
fun NavigationHost(navHostController: NavHostController, startNavigationRoute: NavigationRoute) {

    NavHost(
        navController = navHostController,
        startDestination = startNavigationRoute.route,
        builder = {

            composable(route = NavigationRoute.Onboarding.route) {
                OnboardingScreen(onNavigate = navHostController::navigate)
            }

            composable(route = NavigationRoute.Login.route){

                Text(text = "Login")
            }


        })
}