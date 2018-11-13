package com.foodever.foodever.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.foodever.foodever.R
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val tag: String = "LoginActivity"
    val username = "admin"
    val password = "admin"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogin.setOnClickListener {
            val username: String = editTextUsername.text.toString()
            val password: String = editTextPassword.text.toString()
            if(username != this@LoginActivity.username || password != this@LoginActivity.password) {
                Toasty.info(this, "Username/Password is incorrect!", Toast.LENGTH_LONG, true).show()
                return@setOnClickListener
            } else {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.putExtra("username", this@LoginActivity.username)
                startActivity(intent)
            }
        }

        buttonFacebook.setOnClickListener {
            Toasty.info(this, "Sign in with Facebook!", Toast.LENGTH_LONG, true).show()
        }

        buttonGoogle.setOnClickListener {
            Toasty.info(this, "Sign in with Google!", Toast.LENGTH_LONG, true).show()
        }

        textViewCreateAccount.setOnClickListener {
            Toasty.info(this, "Create an account!", Toast.LENGTH_LONG, true).show()
        }
    }
}
