package com.example.sipcalculator.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
    val inflationError = remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    val buttonEnabled = remember(
        viewModel.monthlyAmount.value, amountError.value,
        viewModel.totalYears.value, yearError.value,
        viewModel.expectedAnnualReturn.value, returnsError.value,
        viewModel.lumpsumAmount.value, lumpsumError.value, viewModel.isLumpsumSelected.value,
        viewModel.inflationRate.value, inflationError.value, viewModel.isInflationSelected.value
    ) {
        !amountError.value && !yearError.value && !returnsError.value
                && viewModel.monthlyAmount.value.isNotEmpty()
                && viewModel.totalYears.value.isNotEmpty()
                && viewModel.expectedAnnualReturn.value.isNotEmpty()

                && (!viewModel.isLumpsumSelected.value or (viewModel.isLumpsumSelected.value
                && !lumpsumError.value && viewModel.lumpsumAmount.value.isNotEmpty()))

                && (!viewModel.isInflationSelected.value or (viewModel.isInflationSelected.value
                && !inflationError.value && viewModel.inflationRate.value.isNotEmpty()))
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
                keyboardActions = KeyboardActions(onDone = { focusManager.moveFocus(FocusDirection.Down) }),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
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
                    modifier = Modifier.weight(66f),
                    singleLine = true,
                    isError = yearError.value,
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.moveFocus(
                            FocusDirection.Right
                        )
                    }),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = DarkGrey)
                )

                Spacer(modifier = Modifier.width(16.dp))

                OutlinedTextField(
                    value = viewModel.expectedAnnualReturn.value,
                    onValueChange = {
                        viewModel.expectedAnnualReturn.value = it
                        returnsError.value = it.toDoubleOrNull() == null
                    },
                    textStyle = Style.textStyleField,
                    label = { Text(text = "Returns (%)") },
                    modifier = Modifier.weight(34f),
                    singleLine = true,
                    isError = returnsError.value,
                    keyboardActions = KeyboardActions(onDone = {
                        if (viewModel.isInflationSelected.value || viewModel.isLumpsumSelected.value)
                            focusManager.moveFocus(FocusDirection.Down)
                        else
                            focusManager.clearFocus()
                    }),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = DarkGrey)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            CheckedBoxWithText("Initial Amount", viewModel.isLumpsumSelected)

            if (viewModel.isLumpsumSelected.value) {
                Spacer(modifier = Modifier.height(4.dp))
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
                    keyboardActions = KeyboardActions(onDone = {
                        if (viewModel.isInflationSelected.value)
                            focusManager.moveFocus(FocusDirection.Down)
                        else
                            focusManager.clearFocus()
                    }),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = DarkGrey)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            CheckedBoxWithText("Inflation Rate", viewModel.isInflationSelected)

            if (viewModel.isInflationSelected.value) {
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = viewModel.inflationRate.value,
                    onValueChange = {
                        viewModel.inflationRate.value = it
                        inflationError.value = it.toDoubleOrNull() == null
                    },
                    textStyle = Style.textStyleField,
                    label = { Text("Inflation (%)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = inflationError.value,
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = DarkGrey)
                )
            }

            Spacer(modifier = Modifier.height(120.dp))

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
        modifier = Modifier.clickable(
            indication = rememberRipple(bounded = true),
            interactionSource = remember { MutableInteractionSource() }
        ) {
            checkedState.value = !checkedState.value
        }
    ) {
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it },
            colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.primary)
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

@Preview("CheckedBoxWithText", device = Devices.DEFAULT)
@Composable
fun CheckedBoxWithTextPreview() {
    CheckedBoxWithText(
        text = "CheckedBoxWithText",
        checkedState = remember { mutableStateOf(true) })
}

@Composable
fun DropDownList(
    requestToOpen: Boolean = false,
    list: List<String>,
    request: (Boolean) -> Unit,
    selectedString: (String) -> Unit
) {
    DropdownMenu(
        modifier = Modifier.fillMaxWidth(),
        expanded = requestToOpen,
        onDismissRequest = { request(false) },
    ) {
        list.forEachIndexed { index, item ->
            DropdownMenuItem(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    request(false)
                    selectedString(item)
                }
            ) {
                Text(item, modifier = Modifier.wrapContentWidth(), textAlign = TextAlign.Start)
            }
        }
    }
}

@Composable
fun CountrySelection() {
    val countryList = listOf(
        "United state",
        "Australia",
        "Japan",
        "India",
    )
    val text = remember { mutableStateOf("") } // initial value
    val isOpen = remember { mutableStateOf(false) } // initial value
    val openCloseOfDropDownList: (Boolean) -> Unit = {
        isOpen.value = it
    }
    val userSelectedString: (String) -> Unit = {
        text.value = it
    }
    Box {
        Column {
            OutlinedTextField(
                value = text.value,
                onValueChange = { text.value = it },
                label = { Text(text = "TextFieldTitle") },
                modifier = Modifier.fillMaxWidth()
            )
            DropDownList(
                requestToOpen = isOpen.value,
                list = countryList,
                openCloseOfDropDownList,
                userSelectedString
            )
        }
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .padding(top = 8.dp)
                .clickable(
                    onClick = { isOpen.value = true },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
        )
    }
}

