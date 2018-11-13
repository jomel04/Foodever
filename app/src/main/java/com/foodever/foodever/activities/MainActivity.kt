package com.foodever.foodever.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.foodever.foodever.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val user = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        user?.let {
            val name: String? = user.displayName
            val email: String? = user.email
            val emailVerified = user.isEmailVerified
            tvResult.setText("Name: $name\nEmail: $email")
        }

        btnLogout.setOnClickListener {
            signOut()
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }
}
