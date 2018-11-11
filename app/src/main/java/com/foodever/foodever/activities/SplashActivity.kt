package com.foodever.foodever.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.foodever.foodever.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splash: Thread = object: Thread() {
            override fun run() {
                try {
                    Thread.sleep(3000)
                    val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                    startActivity(intent)
                } catch(e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        splash.start()
    }
}
