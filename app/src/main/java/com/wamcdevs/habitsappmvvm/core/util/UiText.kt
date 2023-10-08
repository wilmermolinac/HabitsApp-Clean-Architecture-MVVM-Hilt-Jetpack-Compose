package com.wamcdevs.habitsapp.core.util

import android.content.Context

// Creamos esta Clase Auxiliar para que nos ayude con los Strings
sealed class UiText {

    // Esta calse Recibe el string
    data class DynamicString(val text: String) : UiText()

    // Esta clase recibe los recursos de String
    data class StringResource(val resId: Int) : UiText()

    // Esta funcion nos ayuda a convertor el UiText a un string
    fun asString(context: Context): String {
        // Y retornamos dependiendo de lo que se este procesando si es una DynamicString o StringResource
        return when (this) {
            is DynamicString -> {
                text
            }

            is StringResource -> {
                context.getString(resId)
            }
        }
    }
}

