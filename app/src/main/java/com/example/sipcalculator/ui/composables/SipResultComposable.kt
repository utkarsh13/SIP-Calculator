package com.example.sipcalculator.ui.composables

import android.icu.text.NumberFormat
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import java.util.*

@ExperimentalComposeApi
@Composable
fun SipResultComposable(items: MutableState<List<String>>) {
    Surface(color = Color(0xffF3F3F3)) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(items = items.value) { index: Int, item: String ->
                Row(
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            top = 6.dp,
                            bottom = 6.dp,
                            end = 16.dp
                        )
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Text(
                        text = "${index + 1} years",
                        style = TextStyle(
                            fontSize = TextUnit(20f, TextUnitType.Sp),
                            fontWeight = FontWeight.Normal
                        ),
                        modifier = Modifier.width(100.dp)
                    )
                    Text(text = item,
                        style = TextStyle(
                            fontSize = TextUnit(20f, TextUnitType.Sp),
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier.width(120.dp),
                        textAlign = TextAlign.Center
                    )
                    val amount = 100000f * index*4

                    val format: NumberFormat =
                        NumberFormat.getCurrencyInstance(Locale("en", "in"))
                    format.maximumFractionDigits = 0
                    val moneyString: String = format.format(amount)

                    Text(text = moneyString,
                        style = TextStyle(
                            fontSize = TextUnit(20f, TextUnitType.Sp),
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier.width(100.dp),
                        textAlign = TextAlign.End
                    )
                }

            }
        }
    }
}

@ExperimentalComposeApi
@Preview("SipResultComposable", device = Devices.DEFAULT)
@Composable
fun SipResultComposablePreview() {
    val items: MutableState<List<String>> = remember { mutableStateOf(listOf("12345675", "12345675", "12345675")) }
    SipResultComposable(items)
}