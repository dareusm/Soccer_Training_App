package com.example.soccerapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class Agility : AppCompatActivity() {


    private lateinit var agility_description: TextView
    private lateinit var back_arrow: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.agility_activity)

        back_arrow = findViewById(R.id.back_arrow)
        back_arrow.setOnClickListener {
            finish()
        }

        val database = FirebaseDatabase.getInstance()
        val postsRef = database.getReference("agility_desc")

        agility_description = findViewById(R.id.agilityDescription)

        //Get value from shooting node in firebase database
        postsRef.child("desc").get().addOnSuccessListener {
            //for (postSnapshot in it.children) {
            val post = it.getValue(String::class.java)
            agility_description.text = post


        }.addOnFailureListener {
            println("Failed to read value.")
        }
    }
}

