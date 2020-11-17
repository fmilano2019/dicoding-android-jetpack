package com.example.moviecatalog.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviecatalog.R
import com.example.moviecatalog.ui.home.HomeActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        GlobalScope.launch {
            delay(1000)
            Intent(this@SplashActivity, HomeActivity::class.java).run {
                startActivity(this)
                finish()
            }
        }
    }
}