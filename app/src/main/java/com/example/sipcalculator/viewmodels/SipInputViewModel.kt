package com.example.sipcalculator.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel

class SipInputViewModel: ViewModel() {

    val totalYears: MutableState<String> = mutableStateOf("")

    val monthlyAmount: MutableState<String> = mutableStateOf("")

    val expectedAnnualReturn: MutableState<String> = mutableStateOf("")

    val lumpsumAmount: MutableState<String> = mutableStateOf("")

    init {

    }
}