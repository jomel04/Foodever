package com.foodever.foodever.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.foodever.foodever.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.math.sign

class LoginActivity : AppCompatActivity() {

    val tag: String = "LoginActivity"
    val username = "admin"
    val password = "admin"

    lateinit var auth: FirebaseAuth
    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        auth = FirebaseAuth.getInstance()

        buttonLogin.setOnClickListener {
//            val username: String = editTextUsername.text.toString()
//            val password: String = editTextPassword.text.toString()
//            if(username != this@LoginActivity.username || password != this@LoginActivity.password) {
//                Toasty.info(this, "Username/Password is incorrect!", Toast.LENGTH_LONG, true).show()
//                return@setOnClickListener
//            } else {
//                val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                intent.putExtra("username", this@LoginActivity.username)
//                startActivity(intent)
//            }
        }

        buttonFacebook.setOnClickListener {
            Toasty.info(this, "Sign in with Facebook!", Toast.LENGTH_LONG, true).show()
        }

        buttonGoogle.setOnClickListener {
            signIn()
        }

        textViewCreateAccount.setOnClickListener {
            Toasty.info(this, "Create an account!", Toast.LENGTH_LONG, true).show()
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, 123)
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 123) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(tag, "Google sign in failed", e)
                // ...
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(tag, "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    Toasty.success(this@LoginActivity, "Successfully logged in!", Toast.LENGTH_SHORT).show()
                } else {
                    Toasty.error(this@LoginActivity, "Login failed!", Toast.LENGTH_LONG).show()
                }
            }
    }
}
