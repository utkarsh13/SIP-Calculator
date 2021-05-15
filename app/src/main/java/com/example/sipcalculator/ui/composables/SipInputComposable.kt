package com.example.sipcalculator.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sipcalculator.theme.DarkGrey
import com.example.sipcalculator.theme.Grey
import com.example.sipcalculator.theme.Style
import com.example.sipcalculator.viewmodels.SipInputViewModel

@Composable
fun SipInputComposable(viewModel: SipInputViewModel, calculateReturns: () -> Unit) {
    val amountError = remember { mutableStateOf(false) }
    val yearError = remember { mutableStateOf(false) }
    val returnsError = remember { mutableStateOf(false) }
    val lumpsumError = remember { mutableStateOf(false) }

    val buttonEnabled = remember(
        viewModel.monthlyAmount.value, amountError.value,
        viewModel.totalYears.value, yearError.value,
        viewModel.expectedAnnualReturn.value, returnsError.value,
        viewModel.lumpsumAmount.value, lumpsumError.value, viewModel.isLumpsumChecked.value
    ) {
        !amountError.value && !yearError.value && !returnsError.value
                && viewModel.monthlyAmount.value.isNotEmpty()
                && viewModel.totalYears.value.isNotEmpty()
                && viewModel.expectedAnnualReturn.value.isNotEmpty()
                && (!viewModel.isLumpsumChecked.value or
                (viewModel.isLumpsumChecked.value && !lumpsumError.value  && viewModel.expectedAnnualReturn.value.isNotEmpty()))
    }


    Surface(color = Color(0xffF3F3F3)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(state = rememberScrollState(), enabled = true)
        ) {

            OutlinedTextField(
                value = viewModel.monthlyAmount.value,
                onValueChange = {
                    viewModel.monthlyAmount.value = it
                    amountError.value = it.toIntOrNull() == null
                },
                textStyle = Style.textStyleField,
                label = { Text("Monthly Investment (₹)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = amountError.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = DarkGrey)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = viewModel.totalYears.value,
                    onValueChange = {
                        viewModel.totalYears.value = it
                        yearError.value = it.toIntOrNull() == null
                    },
                    textStyle = Style.textStyleField,
                    label = { Text("Investment Period (years)") },
                    modifier = Modifier.fillMaxWidth(0.6f),
                    singleLine = true,
                    isError = yearError.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = DarkGrey)
                )

                Spacer(modifier = Modifier.width(20.dp))

                OutlinedTextField(
                    value = viewModel.expectedAnnualReturn.value,
                    onValueChange = {
                        viewModel.expectedAnnualReturn.value = it
                        returnsError.value = it.toDoubleOrNull() == null
                    },
                    textStyle = Style.textStyleField,
                    label = { Text(text = "Returns (%)") },
                    modifier = Modifier.fillMaxWidth(1f),
                    singleLine = true,
                    isError = returnsError.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = DarkGrey)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            CheckedBoxWithText("Initial Amount", viewModel.isLumpsumChecked)

            if (viewModel.isLumpsumChecked.value) {
                OutlinedTextField(
                    value = viewModel.lumpsumAmount.value,
                    onValueChange = {
                        viewModel.lumpsumAmount.value = it
                        lumpsumError.value = it.toIntOrNull() == null
                    },
                    textStyle = Style.textStyleField,
                    label = { Text("Initial Investment Amount (₹)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = lumpsumError.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = DarkGrey)
                )
            }

            Spacer(modifier = Modifier.height(164.dp))

            Button(
                onClick = { calculateReturns() },
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally),
                enabled = buttonEnabled,
                colors = ButtonDefaults.buttonColors(disabledContentColor = Grey)
            ) {
                Text(
                    text = "Calculate projected SIP returns",
                    style = Style.buttonStyle
                )
            }
        }
    }
}

@Composable
fun CheckedBoxWithText(text: String, checkedState: MutableState<Boolean>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { checkedState.value = !checkedState.value }
    ) {
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it },
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            style = Style.textStyleYear

        )
    }
}

@Preview("SipInputComposable", device = Devices.DEFAULT)
@Composable
fun SipInputComposablePreview() {
    SipInputComposable(SipInputViewModel()) {}
}

