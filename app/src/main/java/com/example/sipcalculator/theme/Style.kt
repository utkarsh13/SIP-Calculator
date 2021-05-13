package com.example.sipcalculator.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp

val typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    )
)



object Style {
    val buttonStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    )

    val textStyleAmount = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = DarkerGrey
    )

    val textStyleYear = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = DarkerGrey
    )

    val textStyleField = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        color = DarkGrey
    )

    val textStyleFieldDropDown = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        color = DarkGrey
    )
}