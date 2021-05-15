package com.example.sipcalculator.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sipcalculator.SipModel
import kotlin.math.pow

class SipResultViewModel: ViewModel() {

    val list: MutableState<MutableList<SipModel>> = mutableStateOf(mutableListOf())

    fun initList(years: Int, monthlyAmount: Int, expectedReturns: Double, lumpsum: Int) {
        list.value.clear()
        val monthlyRate = expectedReturns / 12 / 100
        val yearlyRate = expectedReturns  / 100

        if (lumpsum != 0) {
            list.value.add(SipModel(0, lumpsum.toDouble(), lumpsum.toDouble()))
        }

        for(year in 1..years) {
            val months = year * 12

            val sipValue = ((monthlyAmount * ((1 + monthlyRate).pow((months)) - 1)/monthlyRate)*(1+monthlyRate))
            val lumpsumValue = lumpsum * ((1.0 + yearlyRate).pow(year))

            val futureValue = sipValue + lumpsumValue

            list.value.add(SipModel(year, monthlyAmount*months+lumpsum.toDouble(), futureValue))
        }
    }


}