package com.example.soccerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.soccerapp.Registration
import com.example.soccerapp.Login
import com.google.firebase.firestore.DocumentSnapshot

class MainActivity : AppCompatActivity() {


    lateinit var trainingBtn: Button
    lateinit var dietBtn: Button
    lateinit var findFieldBtn: Button
    lateinit var reqSessionBtn: Button
    lateinit var title: TextView
    lateinit var firstName: String
    lateinit var profileIcon: ImageView
    lateinit var backArrow: ImageView
    var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val intent = intent
        val firstName = intent.getStringExtra("firstName")
        if (firstName != null) {
            val welcomeTitle = findViewById<TextView>(R.id.welcome_title)
            welcomeTitle.text = "Welcome $firstName"
        }

        trainingBtn = findViewById(R.id.training_btn)
        dietBtn = findViewById(R.id.diet_btn)
        findFieldBtn = findViewById(R.id.find_field_btn)
        reqSessionBtn = findViewById(R.id.request_session_btn)
        profileIcon = findViewById(R.id.profile_Icon)
        backArrow = findViewById(R.id.back_arrow)

        backArrow.setOnClickListener {
            finish()
        }

        profileIcon.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        trainingBtn.setOnClickListener {
            val intent = Intent(this, TrainingActivity::class.java)
            startActivity(intent)
        }

        dietBtn.setOnClickListener {
            val intent = Intent(this, Diet::class.java)
            startActivity(intent)
        }

        findFieldBtn.setOnClickListener {
            val intent = Intent(this, FindFieldActivity::class.java)
            startActivity(intent)
        }

        reqSessionBtn.setOnClickListener {
            val intent = Intent(this, RequestSession::class.java)
            startActivity(intent)
        }

    }

}

