package com.example.sipcalculator.ui.composables

import android.icu.text.NumberFormat
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sipcalculator.SipModel
import com.example.sipcalculator.theme.Style
import java.util.*

@Composable
fun SipResultComposable(items: List<SipModel>) {
    val format = NumberFormat.getCurrencyInstance(Locale("en", "in"))
    format.maximumFractionDigits = 0
    Surface(color = Color(0xffF3F3F3)) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(items = items) { index: Int, item: SipModel ->

                Spacer(modifier = Modifier.height(4.dp))

                Card(modifier = Modifier.padding(start = 4.dp, end = 4.dp)) {

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
                            text = "${item.years} years",
                            style = Style.textStyleYear,
                            modifier = Modifier.width(80.dp)
                        )

                        Text(
                            text = format.format(item.invested),
                            style = Style.textStyleAmount,
                            modifier = Modifier.width(130.dp),
                            textAlign = TextAlign.End
                        )

                        Text(
                            text = format.format(item.finalAmount),
                            style = Style.textStyleAmount,
                            modifier = Modifier.width(135.dp),
                            textAlign = TextAlign.End
                        )
                    }
                }

            }
        }
    }
}

@Preview("SipResultComposable", device = Devices.DEFAULT)
@Composable
fun SipResultComposablePreview() {
    SipResultComposable(
        listOf(
            SipModel(1, 120000, 128608),
            SipModel(1, 240000, 128608),
            SipModel(1, 360000, 128608),
        )
    )
}