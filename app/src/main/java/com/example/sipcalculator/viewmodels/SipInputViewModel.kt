package com.example.sipcalculator.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.sipcalculator.model.TopupType

class SipInputViewModel: ViewModel() {

    val totalYears: MutableState<String> = mutableStateOf("")
    val monthlyAmount: MutableState<String> = mutableStateOf("")
    val expectedAnnualReturn: MutableState<String> = mutableStateOf("")
    val isLumpsumSelected: MutableState<Boolean> = mutableStateOf(false)
    val lumpsumAmount: MutableState<String> = mutableStateOf("")
    val isInflationSelected: MutableState<Boolean> = mutableStateOf(false)
    val inflationRate: MutableState<String> = mutableStateOf("")
    val isTopupSelected: MutableState<Boolean> = mutableStateOf(false)
    val isTopupDropdownOpen: MutableState<Boolean> = mutableStateOf(false)
    val topupType: MutableState<TopupType> = mutableStateOf(TopupType.PERCENTAGE)
    val topupValue: MutableState<String> = mutableStateOf("")

    init {

    }
}