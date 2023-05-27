package com.example.soccerapp

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Profile: AppCompatActivity() {

    lateinit var back_arrow: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)

        back_arrow = findViewById(R.id.back_arrow)
        back_arrow.setOnClickListener {
            finish()
        }

    }
}