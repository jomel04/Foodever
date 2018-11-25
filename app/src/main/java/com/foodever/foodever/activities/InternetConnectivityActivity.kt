package com.foodever.foodever.activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.foodever.foodever.R
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_internet_connectivity.*

class InternetConnectivityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internet_connectivity)

        btnTryAgain.setOnClickListener {
            if (isOnline()) {
                val intent = Intent(baseContext, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toasty.error(this, "No internet connection!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isOnline(): Boolean {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}
