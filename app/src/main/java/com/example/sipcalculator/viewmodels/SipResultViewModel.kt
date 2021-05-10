package com.example.sipcalculator.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sipcalculator.model.InputDataModel
import com.example.sipcalculator.model.SipModel
import kotlin.math.pow

class SipResultViewModel : ViewModel() {

    val list: MutableState<MutableList<SipModel>> = mutableStateOf(mutableListOf())

    fun initList(inputData: InputDataModel) {
        list.value.clear()
        val yearlyRate = (inputData.expectedReturns - inputData.inflationRate) / 100
        val monthlyRate = yearlyRate / 12
        val topupRate = inputData.topupRate / 100

        if (inputData.lumpsum != 0.0) {
            list.value.add(SipModel(0, inputData.lumpsum, inputData.lumpsum))
        }

        var previousYrMonthlyAmount = 0.0
        var previousYrSipValue = 0.0

        for (year in 1..inputData.years) {
            val monthlyAmount = inputData.monthlyAmount * ((1.0 + topupRate).pow(year-1)) + (inputData.topupAmount*(year-1))


            val lastYrSipCurrentValue = previousYrSipValue * ((1.0 + monthlyRate).pow(12))

            val sipValue =
                ((monthlyAmount * ((1 + monthlyRate).pow((12)) - 1) / monthlyRate) * (1 + monthlyRate))
            val lumpsumValue = inputData.lumpsum * ((1.0 + yearlyRate).pow(year))


            val investedValue = monthlyAmount * 12 + previousYrMonthlyAmount + inputData.lumpsum
            val futureValue = sipValue + lastYrSipCurrentValue + lumpsumValue

            previousYrMonthlyAmount += (monthlyAmount * 12).toInt()
            previousYrSipValue = sipValue + lastYrSipCurrentValue

            list.value.add(SipModel(year, investedValue, futureValue))
        }
    }


}