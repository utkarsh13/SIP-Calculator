package com.example.sipcalculator.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sipcalculator.R
import com.example.sipcalculator.ui.fragments.SipInputFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SipInputFragment())
            .commit()

    }
}

