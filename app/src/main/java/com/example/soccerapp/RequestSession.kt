package com.example.soccerapp

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.type.DateTime

class RequestSession : AppCompatActivity() {

    lateinit var back_arrow: ImageView
    lateinit var calendar: CalendarView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.request_session_activity)

        back_arrow = findViewById(R.id.back_arrow)
        back_arrow.setOnClickListener {
            finish()
        }

        calendar = findViewById<CalendarView>(R.id.calendarView)
        //Set the date to today
        calendar.setDate(System.currentTimeMillis(), false, true)

        var option1 = findViewById<RadioButton>(R.id.HourSelector1)
        var option2 = findViewById<RadioButton>(R.id.HourSelector2)

        var calendarClicked = false
        //Set the listener for when the user selects a date
        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val showPopupButton = findViewById<Button>(R.id.request_session_button)
            var a = month
            var b = dayOfMonth
            var c = year
            calendarClicked = true
            showPopupButton.setOnClickListener {
                if (calendarClicked == false) {
                    Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show()
                } else
                    if(option1.isChecked){
                        option1.setOnClickListener {
                        option2.setChecked(false)
                        var d = 1
                        openDialog(a, b, c, d)
                    }
                    } else if(option2.isChecked){
                    option2.setOnClickListener {
                        option1.setChecked(false)
                        var d = 2
                        openDialog(a, b, c, d)
                    }

                    } else {
                        Toast.makeText(this, ("Please select a session length"), Toast.LENGTH_SHORT).show()
                    }

            }
        }
    }

    private fun openDialog(month: Int, date: Int, year: Int, hour: Int) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.popup)
        //Find title from pop.xml
        dialog.findViewById<TextView>(R.id.description).text = "Session length: $hour Hours \n Date: $month/$date/$year"
        dialog.show()
    }
}