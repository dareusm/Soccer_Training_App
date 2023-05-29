package com.example.soccerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TrainingActivity : AppCompatActivity() {

    lateinit var dribblingBtn: Button
    lateinit var passingBtn: Button
    lateinit var defenseBtn: Button
    lateinit var agilityBtn: Button
    lateinit var shootingBtn: Button
    lateinit var strengthBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.training_activity)

        dribblingBtn = findViewById(R.id.dribbling_button)
        passingBtn = findViewById(R.id.passing_button)
        defenseBtn = findViewById(R.id.defending_button)
        agilityBtn = findViewById(R.id.agility_button)
        shootingBtn = findViewById(R.id.shooting_button)
        strengthBtn = findViewById(R.id.strength_button)

        dribblingBtn.setOnClickListener {
            val intent = Intent(this, Dribbling::class.java)
            startActivity(intent)
        }

        passingBtn.setOnClickListener {
            val intent = Intent(this, Passing::class.java)
            startActivity(intent)
        }

        defenseBtn.setOnClickListener {
            val intent = Intent(this, Defense::class.java)
            startActivity(intent)
        }

        shootingBtn.setOnClickListener {
            val intent = Intent(this, Shooting::class.java)
            startActivity(intent)
        }

        strengthBtn.setOnClickListener {
            val intent = Intent(this, Strength::class.java)
            startActivity(intent)
        }

        agilityBtn.setOnClickListener {
            val intent = Intent(this, Agility::class.java)
            startActivity(intent)
        }

    }
}