package com.arena.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.arena.R

val Rethink = FontFamily(
    Font(R.font.rethinksans_regular, FontWeight.Thin),
    Font(R.font.rethinksans_italic, FontWeight.Thin, style = FontStyle.Italic),
    Font(R.font.rethinksans_regular, FontWeight.ExtraLight),
    Font(R.font.rethinksans_italic, FontWeight.ExtraLight, style = FontStyle.Italic),
    Font(R.font.rethinksans_regular, FontWeight.Light),
    Font(R.font.rethinksans_italic, FontWeight.Light, style = FontStyle.Italic),
    Font(R.font.rethinksans_regular, FontWeight.Normal),
    Font(R.font.rethinksans_italic, FontWeight.Normal, style = FontStyle.Italic),
    Font(R.font.rethinksans_medium, FontWeight.Medium),
    Font(R.font.rethinksans_mediumitalic, FontWeight.Medium, style = FontStyle.Italic),
    Font(R.font.rethinksans_semibold, FontWeight.SemiBold),
    Font(R.font.rethinksans_semibolditalic, FontWeight.SemiBold, style = FontStyle.Italic),
    Font(R.font.rethinksans_bold, FontWeight.Bold),
    Font(R.font.rethinksans_bolditalic, FontWeight.Bold, style = FontStyle.Italic),
    Font(R.font.rethinksans_extrabold, FontWeight.ExtraBold),
    Font(R.font.rethinksans_extrabolditalic, FontWeight.ExtraBold, style = FontStyle.Italic),
    Font(R.font.rethinksans_extrabold, FontWeight.Black),
    Font(R.font.rethinksans_extrabolditalic, FontWeight.Black, style = FontStyle.Italic)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Rethink,
        fontWeight = FontWeight.Black,
        fontSize = 30.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Rethink,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Rethink,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = Rethink,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Rethink,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Rethink,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Rethink,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Rethink,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Rethink,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Rethink,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Rethink,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Rethink,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Rethink,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    )
)
