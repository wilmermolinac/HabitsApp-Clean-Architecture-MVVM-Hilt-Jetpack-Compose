package com.wamcdevs.habitsapp.core.util

// Clase encargada de los eventos de la ui
sealed class UiEvent {
    data class Navigate(val route: String) : UiEvent()
    object NavigateUp : UiEvent()
    data class ShowSnackBar(val message: UiText): UiEvent()
}
