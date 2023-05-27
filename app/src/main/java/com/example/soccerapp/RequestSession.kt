package com.example.soccerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class RequestSession : AppCompatActivity() {

    lateinit var back_arrow: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.request_session_activity)

        back_arrow = findViewById(R.id.back_arrow)
        back_arrow.setOnClickListener {
            finish()
        }
    }
}