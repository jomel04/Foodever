package com.foodever.foodever.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.foodever.foodever.R
import com.google.firebase.auth.FirebaseAuth
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    val TAG: String = "RegisterActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val username = etUsername.text.toString()
        val email = etRegisterEmail.text.toString()
        val password = etRegisterPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toasty.info(this, "Please fill out the fields!", Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                Toasty.info(this, "Successfully registered!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toasty.info(this, "Registration failed: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
