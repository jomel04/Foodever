package com.foodever.foodever.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.foodever.foodever.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult.setText(intent.getStringExtra("username"))
    }
}