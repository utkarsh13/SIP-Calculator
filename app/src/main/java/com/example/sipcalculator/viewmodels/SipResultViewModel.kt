package com.example.sipcalculator.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sipcalculator.SipModel

class SipResultViewModel: ViewModel() {

    val list: MutableState<List<SipModel>> = mutableStateOf(listOf())

    fun initList(years: Int, monthlyAmount: Int, expectedReturns: Double) {

    }


}