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
        val topupRate = inputData.topupRate / 12

        if (inputData.lumpsum != 0) {
            list.value.add(SipModel(0, inputData.lumpsum.toDouble(), inputData.lumpsum.toDouble()))
        }

        var previousMonthlyAmount = 0
//        var previousSipValue = 0.0

        for (year in 1..inputData.years) {
            val months = year * 12
            val monthlyAmount = inputData.monthlyAmount * ((1.0 + topupRate).pow(year-1)) + (inputData.topupAmount*(year-1))



            val sipValue =
                ((monthlyAmount * ((1 + monthlyRate).pow((months)) - 1) / monthlyRate) * (1 + monthlyRate))
            val lumpsumValue = inputData.lumpsum * ((1.0 + yearlyRate).pow(year))


            val investedValue = monthlyAmount * 12 + previousMonthlyAmount + inputData.lumpsum.toDouble()
            val futureValue = sipValue + lumpsumValue



            previousMonthlyAmount += (monthlyAmount * 12).toInt()
//            previousSipValue = sipValue


            list.value.add(SipModel(year, investedValue, futureValue))
        }
    }


}