package com.example.sipcalculator

import android.content.res.Resources
import android.util.TypedValue

object Utils {

    fun getPxForDp(res: Resources, dp: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            res.displayMetrics
        )
    }

    fun getPxForSp(res: Resources, sp: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            sp.toFloat(),
            res.displayMetrics
        )
    }

}