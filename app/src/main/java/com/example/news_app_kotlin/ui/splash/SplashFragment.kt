package com.example.news_app_kotlin.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.news_app_kotlin.R
import com.example.news_app_kotlin.ui.auth.LoginActivity
import com.example.news_app_kotlin.ui.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_splash)

        Handler(Looper.getMainLooper()).postDelayed({

            startActivity(Intent(this, LoginActivity::class.java))
            finish()

        }, 2500) // 2.5 seconds delay
    }
}
