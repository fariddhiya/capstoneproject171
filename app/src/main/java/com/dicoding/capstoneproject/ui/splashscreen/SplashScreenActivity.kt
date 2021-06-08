package com.dicoding.capstoneproject.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.dicoding.capstoneproject.MainActivity
import com.dicoding.capstoneproject.R
import com.dicoding.capstoneproject.ui.walkthrough.SharedPreferences
import com.dicoding.capstoneproject.ui.walkthrough.WalkthroughActivity

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var pre: SharedPreferences
    private val delayMillis: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        supportActionBar?.hide()

        pre = SharedPreferences(this)
        Handler(Looper.getMainLooper()).postDelayed({
            var intent = Intent()
            if(!pre.firstInstall){
                intent = Intent(this@SplashScreenActivity, WalkthroughActivity::class.java)
            } else {
                intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, delayMillis)
    }
}