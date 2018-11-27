package com.foodever.foodever.activities

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.foodever.foodever.R
import com.foodever.foodever.activities.`class`.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private val TAG: String = "RegisterActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister.setOnClickListener {
            register()
        }

        tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnSelectPhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
    }

    private var selectedPhoto: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhoto = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhoto)
            ivCircleImage.setImageBitmap(bitmap)
            ivCircleImage.alpha = 1f

            Log.d(TAG, "SELECTED PHOTO: $selectedPhoto")
        }
    }

    private fun register() {
        val username = etUsername.text.toString()
        val email = etRegisterEmail.text.toString()
        val password = etRegisterPassword.text.toString()

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toasty.info(this, "Please fill out the fields!", Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                Toasty.info(this, "Successfully registered!", Toast.LENGTH_SHORT).show()
                uploadImage()
            }
            .addOnFailureListener {
                Toasty.info(this, "Registration failed: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun uploadImage() {
        if (selectedPhoto == null) return
        val filename = UUID.randomUUID().toString()
        val reference = FirebaseStorage.getInstance().getReference("/images/$filename")

        reference.putFile(selectedPhoto!!)
            .addOnSuccessListener {
                Log.d(TAG, "Successfully uploaded image: ${it.metadata?.path}")
                reference.downloadUrl.addOnSuccessListener {
                    saveUserProfile(it.toString())
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "Failed to upload image ${it.message}")
            }
    }

    private fun saveUserProfile(profileImageUrl: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val reference = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = User(uid, etUsername.text.toString(), profileImageUrl)
        reference.setValue(user)
            .addOnSuccessListener {
                Log.d(TAG, "Successfully inserted in database!")
            }
    }
}
