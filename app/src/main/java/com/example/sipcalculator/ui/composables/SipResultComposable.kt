package com.example.sipcalculator.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sipcalculator.Utils
import com.example.sipcalculator.model.SipModel
import com.example.sipcalculator.theme.*

@Composable
fun SipResultComposable(items: List<SipModel>) {
    SipCalculatorTheme {
        Surface(color = MaterialTheme.colors.surface) {
            Column {
                HeaderRow()

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    itemsIndexed(items = items) { _, item: SipModel ->
                        Spacer(modifier = Modifier.height(8.dp))

                        Card(
                            modifier = Modifier.padding(
                                start = 8.dp, end = 8.dp, top = 0.dp
                            ),
                            elevation = if (item.years % 5 == 0 && item.years != 0) 8.dp else 0.dp,
                            backgroundColor = MaterialTheme.colors.surface
                        ) {

                            Row(
                                modifier = Modifier
                                    .padding(
                                        start = 10.dp,
                                        top = 9.dp,
                                        bottom = 9.dp,
                                        end = 10.dp
                                    )
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween

                            ) {
                                Text(
                                    text = "${item.years} years",
                                    style = Style.textStyleYear,
                                    modifier = Modifier.weight(14f),
                                )

                                Spacer(modifier = Modifier.width(20.dp))

                                Text(
                                    text = Utils.getMoneyInWords(item.invested),
                                    style = Style.textStyleAmount,
                                    modifier = Modifier.weight(20f),
                                    textAlign = TextAlign.Center,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Spacer(modifier = Modifier.width(16.dp))

                                Text(
                                    text = Utils.getMoneyInWords(item.finalAmount),
                                    style = Style.textStyleAmount,
                                    modifier = Modifier.weight(18f),
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
    }
}

@Composable
fun HeaderRow() {
    Surface(color = MaterialTheme.colors.secondaryVariant, elevation = 4.dp) {
        Row(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = 12.dp,
                    bottom = 12.dp,
                    end = 16.dp
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "DURATION",
                style = Style.textStyleHeaderRow,
                modifier = Modifier.weight(11f),
                color = MaterialTheme.colors.onSecondary
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "AMOUNT INVESTED",
                style = Style.textStyleHeaderRow,
                modifier = Modifier.weight(20f),
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.onSecondary
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "FUTURE VALUE",
                style = Style.textStyleHeaderRow,
                modifier = Modifier.weight(16f),
                textAlign = TextAlign.End,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.onSecondary
            )
        }
    }
}

@Preview("SipResultComposable", device = Devices.DEFAULT)
@Composable
fun SipResultComposablePreview() {
    SipCalculatorTheme {
        SipResultComposable(
            listOf(
                SipModel(1, 18608456.0, 128608123456.0),
                SipModel(2, 240000.0, 1286080000000.0),
                SipModel(3, 36000.0, 1286008.0),
                SipModel(4, 18608456.0, 128608123456.0),
                SipModel(5, 240000.0, 1286080000000.0),
                SipModel(6, 36000.0, 1286008.0),
                SipModel(7, 18623456.0, 128608123456.0),
                SipModel(8, 240000.0, 1286080000000.0),
                SipModel(9, 36000.0, 1286008.0),
                SipModel(10, 186023456.0, 128608123456.0),
                SipModel(11, 240000.0, 1286080000000.0),
                SipModel(122, 36000.0, 1286008.0),
            )
        )
    }
}

@Preview("HeaderRow", device = Devices.DEFAULT)
@Composable
fun HeaderRowPreview() {
    HeaderRow()
}