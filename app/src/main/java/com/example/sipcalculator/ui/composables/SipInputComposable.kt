package com.example.sipcalculator.ui.composables

import android.annotation.SuppressLint
import android.util.Log
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
import com.example.sipcalculator.theme.BgColor
import com.example.sipcalculator.theme.DarkGrey
import com.example.sipcalculator.theme.Grey
import com.example.sipcalculator.theme.Style
import com.example.sipcalculator.viewmodels.SipInputViewModel

@SuppressLint("DefaultLocale")
@Composable
fun SipInputComposable(vm: SipInputViewModel, calculateReturns: () -> Unit) {
    val focusManager = LocalFocusManager.current

    val buttonEnabled = remember(arrayOf(*vm.buttonEnabledCalculation)) {
        vm.getButtonEnabled()
    }

    Surface(color = BgColor) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(state = rememberScrollState(), enabled = true)
        ) {

            //MONTHLY INVESTMENT
            OutlinedTextField(
                value = vm.monthlyAmount.value,
                onValueChange = {
                    vm.monthlyAmount.value = it
                    vm.amountError.value = it.toIntOrNull() == null
                },
                textStyle = Style.textStyleField,
                label = { Text("Monthly Investment (₹)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = vm.amountError.value,
                keyboardActions = KeyboardActions(onDone = { focusManager.moveFocus(FocusDirection.Down) }),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = DarkGrey)
            )

            Spacer(modifier = Modifier.height(16.dp))

            //INVESTMENT PERIOD, RETURNS
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                //INVESTMENT PERIOD
                OutlinedTextField(
                    value = vm.totalYears.value,
                    onValueChange = {
                        vm.totalYears.value = it
                        vm.yearError.value = it.toIntOrNull() == null
                    },
                    textStyle = Style.textStyleField,
                    label = { Text("Investment Period (years)") },
                    modifier = Modifier.weight(64f),
                    singleLine = true,
                    isError = vm.yearError.value,
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

                //RETURNS
                OutlinedTextField(
                    value = vm.expectedAnnualReturn.value,
                    onValueChange = {
                        vm.expectedAnnualReturn.value = it
                        vm.returnsError.value = it.toDoubleOrNull() == null
                    },
                    textStyle = Style.textStyleField,
                    label = { Text(text = "Returns (%)") },
                    modifier = Modifier.weight(36f),
                    singleLine = true,
                    isError = vm.returnsError.value,
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

            //INITIAL AMOUNT
            CheckedBoxWithText("Initial Amount", vm.isLumpsumSelected)

            if (vm.isLumpsumSelected.value) {
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = vm.lumpsumAmount.value,
                    onValueChange = {
                        vm.lumpsumAmount.value = it
                        vm.lumpsumError.value = it.toIntOrNull() == null
                    },
                    textStyle = Style.textStyleField,
                    label = { Text("Initial Investment Amount (₹)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = vm.lumpsumError.value,
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

            //INFLATION RATE
            CheckedBoxWithText("Inflation Rate", vm.isInflationSelected)

            if (vm.isInflationSelected.value) {
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = vm.inflationRate.value,
                    onValueChange = {
                        vm.inflationRate.value = it
                        vm.inflationError.value = it.toDoubleOrNull() == null
                    },
                    textStyle = Style.textStyleField,
                    label = { Text("Inflation (%)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = vm.inflationError.value,
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

            //TOPUP
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
                                vm.topupError.value = it.toDoubleOrNull() == null
                            else
                                vm.topupError.value = it.toIntOrNull() == null
                        },
                        textStyle = Style.textStyleField,
                        label = { Text(vm.topupType.value.text) },
                        modifier = Modifier.weight(64f),
                        singleLine = true,
                        isError = vm.topupError.value,
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = DarkGrey)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    //TOPUP TYPE
                    Box(modifier = Modifier.weight(36f)) {
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
                                    vm.topupError.value = vm.topupValue.value.toDoubleOrNull() == null
                                else
                                    vm.topupError.value = vm.topupValue.value.toIntOrNull() == null
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

            //BUTTON
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