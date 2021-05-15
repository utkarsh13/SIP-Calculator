package com.example.sipcalculator.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sipcalculator.SipModel
import kotlin.math.pow

class SipResultViewModel: ViewModel() {

    val list: MutableState<MutableList<SipModel>> = mutableStateOf(mutableListOf())

    fun initList(years: Int, monthlyAmount: Int, expectedReturns: Double) {
        list.value.clear()
        for(year in 1..years) {
            val monthlyRate = expectedReturns / 12 / 100;  //Rate of interest
            val months = year * 12;  //Time period

            val futureValue = monthlyAmount * ((1.0 + monthlyRate).pow(months) - 1) / monthlyRate

            list.value.add(SipModel(year, monthlyAmount*months, futureValue.toInt()))
        }
    }


}