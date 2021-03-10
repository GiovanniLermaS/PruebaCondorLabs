package com.example.pruebacondorlabs.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebacondorlabs.R
import com.example.pruebacondorlabs.view.main.MainActivity

class SplashActivity : AppCompatActivity() {

    internal companion object {
        private const val SPLASH_DELAY = 2000L
    }

    private val handler = Handler()
    private val runnable = Runnable {
        if (!isFinishing) {
            redirect()
        }
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        handler.postDelayed(runnable, SPLASH_DELAY)
    }

    private fun redirect() {
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
        finish()
    }
}