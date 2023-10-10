package com.wamcdevs.habitsappmvvm.navigation

import androidx.navigation.NavController
import com.wamcdevs.habitsapp.core.util.UiEvent

// Creamos esta funcion de extencion donde recibira un evento UiEvent
fun NavController.navigate(event: UiEvent) {

    // Validamos segun sea el caso del evento UiEvent
    when (event) {

        is UiEvent.Navigate -> {

            // this es que hace referencia al NavController

            // Si es para navegar a una ruta, realizamos la naveegacion a esa screen
            // pasandole la ruta a navigate
            this.navigate(event.route) {

                if (event.route == NavigationRoute.Login.route){

                    // Delete historial of navegation
                    popUpTo(graph.id)
                    {
                        inclusive = true
                    }
                }

            }

        }

        UiEvent.NavigateUp -> {
            // Si este es el caso navegamos a la pantalla anterior
            this.navigateUp()
        }

        is UiEvent.ShowSnackBar -> {
            // Si este es el caso no hacemos nada

           Unit
        }
    }

}