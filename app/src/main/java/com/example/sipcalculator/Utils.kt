package com.example.sipcalculator

import android.content.res.Resources
import android.icu.text.NumberFormat
import android.util.TypedValue
import java.util.*

object Utils {

    fun getMoneyInWords(number: Double): String {
        when {
            number >= 10000000 -> {
                return "₹ " + String.format("%.2f", number/10000000) + " Cr"
            }
            number >= 100000 -> {
                return "₹ " + String.format("%.2f", number/100000) + " L"
            }
            number >= 10000 -> {
                return "₹ " + String.format("%.2f", number/1000) + " K"
            }
        }
        val format = NumberFormat.getCurrencyInstance(Locale("en", "in"))
        format.maximumFractionDigits = 0
        return format.format(number)
    }

}