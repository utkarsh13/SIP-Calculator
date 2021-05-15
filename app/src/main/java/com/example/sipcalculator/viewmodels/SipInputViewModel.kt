package com.example.sipcalculator.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel

class SipInputViewModel: ViewModel() {

    val totalYears: MutableState<String> = mutableStateOf("")
    val monthlyAmount: MutableState<String> = mutableStateOf("")
    val expectedAnnualReturn: MutableState<String> = mutableStateOf("")
    val isLumpsumChecked: MutableState<Boolean> = mutableStateOf(false)
    val lumpsumAmount: MutableState<String> = mutableStateOf("")
    val isInflationChecked: MutableState<Boolean> = mutableStateOf(false)
    val inflationRate: MutableState<String> = mutableStateOf("")

    init {

    }
}