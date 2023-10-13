package com.wamcdevs.habitsappmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wamcdevs.habitsappmvvm.navigation.NavigationHost
import com.wamcdevs.habitsappmvvm.navigation.NavigationRoute
import com.wamcdevs.habitsappmvvm.ui.theme.HabitsAppMVVMTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var navHostController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitsAppMVVMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {


                    navHostController = rememberNavController()

                    NavigationHost(
                        navHostController = navHostController,
                        startNavigationRoute = getStartDestination()
                    )

                }
            }
        }
    }

    private fun getStartDestination(): NavigationRoute {

        //          Si es true
        if (viewModel.isLoggedIn) {
            // ingresamos directamente al home
            return NavigationRoute.Home
        }

        return if (viewModel.stateHasSeenOnboarding) {
            // Aqui validamos si el Onboarding se ha completado
            NavigationRoute.Login
        } else {
            // De lo contrario seguimos en el Onboarding
            NavigationRoute.Onboarding
        }

    }

}