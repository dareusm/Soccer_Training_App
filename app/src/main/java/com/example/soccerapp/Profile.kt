package com.example.soccerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class Profile: AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var back_arrow: ImageView
    private lateinit var editProfileBtn: Button
    private lateinit var logoutBtn: Button
    private lateinit var profileName: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)

        back_arrow = findViewById(R.id.back_arrow)
        back_arrow.setOnClickListener {
            finish()
        }

        auth = FirebaseAuth.getInstance()
        getUser()
        /*val intent = intent
        val firstName = intent.getStringExtra("firstName")
        if (firstName != null) {
            profileName = findViewById(R.id.profile_name)
            profileName.text = "$firstName"
        }*/

        editProfileBtn = findViewById(R.id.edit_profile_button)
        editProfileBtn.setOnClickListener {

        }

        logoutBtn = findViewById(R.id.logout_button)
        logoutBtn.setOnClickListener {
            val auth = FirebaseAuth.getInstance()
            auth.signOut()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

    }

    private fun getUser() {
        val db = FirebaseFirestore.getInstance()
        val userID = auth.currentUser?.uid

        if (userID != null) {
            val userRef = db.collection("users").document(userID)
            userRef.get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val firstName = document.getString("firstName")
                        if (firstName != null) {
                            profileName = findViewById(R.id.profile_name)
                            profileName.text = firstName
                        } else {
                            Toast.makeText(this, "User first name not found", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "User document not found", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "Failed to get user: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "User ID not found", Toast.LENGTH_SHORT).show()
        }
    }
}