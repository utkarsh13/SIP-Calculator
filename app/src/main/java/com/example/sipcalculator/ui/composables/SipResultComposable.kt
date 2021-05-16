package com.example.sipcalculator.ui.composables

import androidx.compose.foundation.BorderStroke
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
import com.example.sipcalculator.Utils
import com.example.sipcalculator.model.SipModel
import com.example.sipcalculator.theme.DarkGrey
import com.example.sipcalculator.theme.Style

@Composable
fun SipResultComposable(items: List<SipModel>) {
    Surface(color = Color(0xffF3F3F3)) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(items = items) { _, item: SipModel ->

                Spacer(modifier = Modifier.height(4.dp))

                Card(
                    modifier = Modifier.padding(
                        start = 4.dp, end = 4.dp, top = 2.dp
                    ),
                    border = if(item.years%5==0 && item.years!=0) BorderStroke(2.dp, DarkGrey) else null
                ) {

                    Row(
                        modifier = Modifier
                            .padding(
                                start = 12.dp,
                                top = 9.dp,
                                bottom = 9.dp,
                                end = 12.dp
                            )
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Text(
                            text = "${item.years} years",
                            style = Style.textStyleYear,
                            modifier = Modifier.weight(11f),
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = Utils.getMoneyInWords(item.invested),
                            style = Style.textStyleAmount,
                            modifier = Modifier.weight(20f),
                            textAlign = TextAlign.End,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = Utils.getMoneyInWords(item.finalAmount),
                            style = Style.textStyleAmount,
                            modifier = Modifier.weight(20f),
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
            SipModel(1, 18608123456.0, 128608123456.0),
            SipModel(2, 240000.0, 1286080000000.0),
            SipModel(3, 36000.0, 1286008.0),
            SipModel(4, 18608123456.0, 128608123456.0),
            SipModel(5, 240000.0, 1286080000000.0),
            SipModel(6, 36000.0, 1286008.0),
            SipModel(7, 18608123456.0, 128608123456.0),
            SipModel(8, 240000.0, 1286080000000.0),
            SipModel(9, 36000.0, 1286008.0),
            SipModel(10, 18608123456.0, 128608123456.0),
            SipModel(11, 240000.0, 1286080000000.0),
            SipModel(122, 36000.0, 1286008.0),
        )
    )
}