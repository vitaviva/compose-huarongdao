package com.jetgame.huarongdao.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)


private val WoodColorPalette = lightColors(
    primary = Wood500,
    primaryVariant = Purple700,
    secondary = Wood700,
    background = Color.LightGray

)

@Composable
fun ComposehuarongdaoTheme(
    theme: Int = 0,
    content: @Composable() () -> Unit
) {

    val (colors, chess) = when (theme % 3) {
        0 -> DarkColorPalette to DarkChess
        1 -> LightColorPalette to LightChess
        2 -> WoodColorPalette to WoodChess
        else -> error("")
    }

    CompositionLocalProvider(LocalChessAssets provides chess) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }

}

