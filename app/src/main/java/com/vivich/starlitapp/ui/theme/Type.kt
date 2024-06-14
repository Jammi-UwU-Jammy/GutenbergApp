package com.vivich.starlitapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vivich.starlitapp.R

val Inter = FontFamily(
    listOf(
        Font(resId =  R.font.literata_medium, weight = FontWeight.Medium)
    )
)

val Bold = FontFamily(
    listOf(
        Font(resId = R.font.literata_bold, weight = FontWeight.Bold)
    )
)

val Italic = FontFamily(
    listOf(
        Font(resId = R.font.literata_italic, weight = FontWeight.Normal)
    )
)


// Set of Material typography styles to start with
val Typography = Typography(

    headlineLarge = TextStyle(
        fontFamily = Bold,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 30.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Bold,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),

    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)