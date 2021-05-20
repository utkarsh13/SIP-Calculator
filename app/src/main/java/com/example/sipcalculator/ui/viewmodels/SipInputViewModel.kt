package com.example.sipcalculator.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sipcalculator.model.TopupType

class SipInputViewModel : ViewModel() {

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

    val amountError = mutableStateOf(false)
    val yearError = mutableStateOf(false)
    val returnsError = mutableStateOf(false)
    val lumpsumError = mutableStateOf(false)
    val inflationError = mutableStateOf(false)
    val topupError = mutableStateOf(false)

    init {

    }
    
    val buttonEnabledCalculation: Array<Any> = arrayOf(
        monthlyAmount.value, amountError.value,
        totalYears.value, yearError.value,
        expectedAnnualReturn.value, returnsError.value,
        lumpsumAmount.value, lumpsumError.value, isLumpsumSelected.value,
        inflationRate.value, inflationError.value, isInflationSelected.value,
        topupValue.value, topupError.value, isTopupSelected.value, topupType.value
    )

    fun getButtonEnabled(): Boolean {
        return !amountError.value && !yearError.value && !returnsError.value
                && monthlyAmount.value.isNotEmpty()
                && totalYears.value.isNotEmpty()
                && expectedAnnualReturn.value.isNotEmpty()

                && (!isLumpsumSelected.value or (isLumpsumSelected.value
                && !lumpsumError.value && lumpsumAmount.value.isNotEmpty()))

                && (!isInflationSelected.value or (isInflationSelected.value
                && !inflationError.value && inflationRate.value.isNotEmpty()))

                && (!isTopupSelected.value or (isTopupSelected.value
                && !topupError.value && topupValue.value.isNotEmpty()))
    }

    fun resetAllFields() {
        totalYears.value = ""
        monthlyAmount.value = ""
        expectedAnnualReturn.value = ""
        isLumpsumSelected.value = false
        lumpsumAmount.value = ""
        isInflationSelected.value = false
        inflationRate.value = ""
        isTopupSelected.value = false
        isTopupDropdownOpen.value = false
        topupType.value = TopupType.PERCENTAGE
        topupValue.value = ""

        amountError.value = false
        yearError.value = false
        returnsError.value = false
        lumpsumError.value = false
        inflationError.value = false
        topupError.value = false
    }
}