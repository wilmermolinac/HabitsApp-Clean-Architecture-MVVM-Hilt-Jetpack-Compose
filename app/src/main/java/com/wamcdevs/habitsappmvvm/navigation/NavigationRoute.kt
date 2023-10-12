package com.wamcdevs.habitsappmvvm.navigation

// Definimos las rutas de nustras Screens
sealed class NavigationRoute(val route:String){

    object Onboarding: NavigationRoute( route = "onboarding")

    object Login: NavigationRoute(route = "login")

    object Home: NavigationRoute(route = "home")
}
