package com.example.sipcalculator.viewmodels

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
        val yearlyRate = (inputData.expectedReturns-inputData.inflationRate) / 100
        val monthlyRate = yearlyRate / 12

        if (inputData.lumpsum != 0) {
            list.value.add(SipModel(0, inputData.lumpsum.toDouble(), inputData.lumpsum.toDouble()))
        }

        for (year in 1..inputData.years) {
            val months = year * 12

            val sipValue =
                ((inputData.monthlyAmount * ((1 + monthlyRate).pow((months)) - 1) / monthlyRate) * (1 + monthlyRate))
            val lumpsumValue = inputData.lumpsum * ((1.0 + yearlyRate).pow(year))

            val futureValue = sipValue + lumpsumValue

            list.value.add(
                SipModel(
                    year,
                    inputData.monthlyAmount * months + inputData.lumpsum.toDouble(),
                    futureValue
                )
            )
        }
    }


}