package com.example.sipcalculator.ui.composables

import android.annotation.SuppressLint
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sipcalculator.model.TopupType
import com.example.sipcalculator.theme.DarkGrey
import com.example.sipcalculator.theme.Grey
import com.example.sipcalculator.theme.Style
import com.example.sipcalculator.viewmodels.SipInputViewModel

@SuppressLint("DefaultLocale")
@Composable
fun SipInputComposable(vm: SipInputViewModel, calculateReturns: () -> Unit) {
    val amountError = remember { mutableStateOf(false) }
    val yearError = remember { mutableStateOf(false) }
    val returnsError = remember { mutableStateOf(false) }
    val lumpsumError = remember { mutableStateOf(false) }
    val inflationError = remember { mutableStateOf(false) }
    val topupError = remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    val buttonEnabled = remember(
        vm.monthlyAmount.value, amountError.value,
        vm.totalYears.value, yearError.value,
        vm.expectedAnnualReturn.value, returnsError.value,
        vm.lumpsumAmount.value, lumpsumError.value, vm.isLumpsumSelected.value,
        vm.inflationRate.value, inflationError.value, vm.isInflationSelected.value,
        vm.topupValue.value, topupError.value, vm.isTopupSelected.value, vm.topupType.value
    ) {
        !amountError.value && !yearError.value && !returnsError.value
                && vm.monthlyAmount.value.isNotEmpty()
                && vm.totalYears.value.isNotEmpty()
                && vm.expectedAnnualReturn.value.isNotEmpty()

                && (!vm.isLumpsumSelected.value or (vm.isLumpsumSelected.value
                && !lumpsumError.value && vm.lumpsumAmount.value.isNotEmpty()))

                && (!vm.isInflationSelected.value or (vm.isInflationSelected.value
                && !inflationError.value && vm.inflationRate.value.isNotEmpty()))

                && (!vm.isTopupSelected.value or (vm.isTopupSelected.value
                && !topupError.value && vm.topupValue.value.isNotEmpty()))
    }


    Surface(color = Color(0xffF3F3F3)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(state = rememberScrollState(), enabled = true)
        ) {

            OutlinedTextField(
                value = vm.monthlyAmount.value,
                onValueChange = {
                    vm.monthlyAmount.value = it
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
                    value = vm.totalYears.value,
                    onValueChange = {
                        vm.totalYears.value = it
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
                    value = vm.expectedAnnualReturn.value,
                    onValueChange = {
                        vm.expectedAnnualReturn.value = it
                        returnsError.value = it.toDoubleOrNull() == null
                    },
                    textStyle = Style.textStyleField,
                    label = { Text(text = "Returns (%)") },
                    modifier = Modifier.weight(34f),
                    singleLine = true,
                    isError = returnsError.value,
                    keyboardActions = KeyboardActions(onDone = {
                        if (vm.isInflationSelected.value || vm.isLumpsumSelected.value || vm.isTopupSelected.value)
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

            CheckedBoxWithText("Initial Amount", vm.isLumpsumSelected)

            if (vm.isLumpsumSelected.value) {
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = vm.lumpsumAmount.value,
                    onValueChange = {
                        vm.lumpsumAmount.value = it
                        lumpsumError.value = it.toIntOrNull() == null
                    },
                    textStyle = Style.textStyleField,
                    label = { Text("Initial Investment Amount (₹)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = lumpsumError.value,
                    keyboardActions = KeyboardActions(onDone = {
                        if (vm.isInflationSelected.value || vm.isTopupSelected.value)
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

            CheckedBoxWithText("Inflation Rate", vm.isInflationSelected)

            if (vm.isInflationSelected.value) {
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = vm.inflationRate.value,
                    onValueChange = {
                        vm.inflationRate.value = it
                        inflationError.value = it.toDoubleOrNull() == null
                    },
                    textStyle = Style.textStyleField,
                    label = { Text("Inflation (%)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = inflationError.value,
                    keyboardActions = KeyboardActions(onDone = {
                        if (vm.isTopupSelected.value)
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

            CheckedBoxWithText("Topup", vm.isTopupSelected)

            if (vm.isTopupSelected.value) {
                Spacer(modifier = Modifier.height(4.dp))


                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    OutlinedTextField(
                        value = vm.topupValue.value,
                        onValueChange = {
                            vm.topupValue.value = it
                            if (vm.topupType.value == TopupType.PERCENTAGE)
                                topupError.value = it.toDoubleOrNull() == null
                            else
                                topupError.value = it.toIntOrNull() == null
                        },
                        textStyle = Style.textStyleField,
                        label = { Text(vm.topupType.value.text) },
                        modifier = Modifier.weight(60f),
                        singleLine = true,
                        isError = topupError.value,
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = DarkGrey)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Box(modifier = Modifier.weight(40f)) {
                        Column() {
                            OutlinedTextField(
                                value = vm.topupType.value.name.toLowerCase().capitalize(),
                                onValueChange = { },
                                textStyle = Style.textStyleFieldDropDown,
                                label = { Text(text = "Topup Type") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(66.dp),
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    unfocusedBorderColor = DarkGrey
                                )
                            )
                            DropDownList(
                                isDropdownOpen = vm.isTopupDropdownOpen,
                                list = TopupType.values().toList()
                            ) {
                                vm.topupType.value = TopupType.values()[it]
                                if (vm.topupType.value == TopupType.PERCENTAGE)
                                    topupError.value = vm.topupValue.value.toDoubleOrNull() == null
                                else
                                    topupError.value = vm.topupValue.value.toIntOrNull() == null
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .matchParentSize()
                                .background(Color.Transparent)
                                .padding(top = 8.dp)
                                .clickable(
                                    onClick = { vm.isTopupDropdownOpen.value = true },
                                    indication = rememberRipple(bounded = true),
                                    interactionSource = remember { MutableInteractionSource() }
                                )
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(64.dp))

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

@Preview("SipInputComposable", device = Devices.DEFAULT)
@Composable
fun SipInputComposablePreview() {
    SipInputComposable(SipInputViewModel()) {}
}