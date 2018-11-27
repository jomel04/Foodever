package com.foodever.foodever.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.foodever.foodever.R
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    val user = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        actionBar?.title = "Me"
        supportActionBar?.title = "Me"

        user?.let {
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            name?.let {
                tvName.text = name
            }
            email?.let {
                tvName.text = email
            }
            Picasso.get().load(photoUrl).into(ivProfile)
            Log.i("UserActivity", "Photo url: $photoUrl")
        }

        btnLogout.setOnClickListener {
            signOut()
            val intent = Intent(this@UserActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }
}
