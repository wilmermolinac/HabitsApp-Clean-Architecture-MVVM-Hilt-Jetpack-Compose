package com.wamcdevs.habitsappmvvm.core

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Aqui en este Data Class implementamos las Dimenciones de nuestra app
data class Dimensions(
    val default: Dp = 0.dp,
    val spaceExtraSmall: Dp = 4.dp,
    val spaceSmall: Dp = 8.dp,
    val spaceMedium: Dp = 16.dp,
    val spaceMiddleLarge: Dp = 24.dp,
    val spaceLarge: Dp = 32.dp,
    val spaceExtraLarge: Dp = 64.dp,
    val spaceExtraLargeMax: Dp = 128.dp
)

// Creamos un espacio local que nos sirve para pasar valores especificos por compositionLocal
val LocalSpacing = compositionLocalOf {
    // Proporcionamos nuestras dimenciones
    Dimensions()
}
