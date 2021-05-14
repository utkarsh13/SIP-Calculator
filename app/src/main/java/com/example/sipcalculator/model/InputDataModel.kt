package com.example.sipcalculator.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InputDataModel(
    val years: Int,
    val monthlyAmount: Double,
    val expectedReturns: Double,
    val lumpsum: Double = 0.0,
    val inflationRate: Double = 0.0,
    val topupAmount: Double = 0.0,
    val topupRate: Double = 0.0
): Parcelable
