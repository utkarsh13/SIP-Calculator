package com.example.sipcalculator.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*

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
        fontSize = 17.sp,
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
        fontWeight = FontWeight.Medium,
        color = DarkGrey
    )

    val textStyleFieldDropDownItem = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = DarkGrey
    )

    val textStyleHeaderRow = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.SansSerif
    )
}