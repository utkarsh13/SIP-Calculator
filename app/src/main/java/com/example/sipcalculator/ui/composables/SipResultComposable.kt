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
import androidx.compose.ui.text.style.TextOverflow
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
            itemsIndexed(items = items) { _, item: SipModel ->

                Spacer(modifier = Modifier.height(4.dp))

                Card(modifier = Modifier.padding(start = 4.dp, end = 4.dp)) {

                    Row(
                        modifier = Modifier
                            .padding(
                                start = 12.dp,
                                top = 6.dp,
                                bottom = 6.dp,
                                end = 12.dp
                            )
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Text(
                            text = "${item.years} years",
                            style = Style.textStyleYear,
                            modifier = Modifier.weight(9f),
                        )

                        Text(
                            text = format.format(item.invested),
                            style = Style.textStyleAmount,
                            modifier = Modifier.weight(20f),
                            textAlign = TextAlign.End,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text = format.format(item.finalAmount),
                            style = Style.textStyleAmount,
                            modifier = Modifier.weight(21f),
                            textAlign = TextAlign.End,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
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
            SipModel(12, 18608123456.0, 128608123456.0),
            SipModel(1, 240000.0, 1286080000000.0),
            SipModel(1, 360000.0, 128608.0),
        )
    )
}