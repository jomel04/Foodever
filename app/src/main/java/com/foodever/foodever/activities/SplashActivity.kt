package com.foodever.foodever.activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.foodever.foodever.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splash: Thread = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(3000)
                    if (isOnline()) {
                        val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        val intent = Intent(this@SplashActivity, InternetConnectivityActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        splash.start()
    }

    private fun isOnline(): Boolean {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}
