package com.example.sipcalculator.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InputDataModel(
    val years: Int,
    val monthlyAmount: Int,
    val expectedReturns: Double,
    val lumpsum: Int = 0,
    val inflationRate: Double = 0.0
): Parcelable
