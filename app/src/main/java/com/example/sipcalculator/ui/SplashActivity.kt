package com.example.sipcalculator.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.runtime.ExperimentalComposeApi
import com.example.sipcalculator.R
import kotlinx.coroutines.*

class SplashActivity: AppCompatActivity() {

    private val uiScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        delayedLaunch()
    }

    private fun delayedLaunch() {
        uiScope.launch {
            delay(300)

            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}