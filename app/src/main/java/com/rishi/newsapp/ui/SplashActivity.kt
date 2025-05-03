package com.rishi.newsapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.rishi.newsapp.BaseActivity
import com.rishi.newsapp.R

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
        }, 3000)
    }
}