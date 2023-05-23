package com.example.soccerapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Registration : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var btnRegister: Button
    private lateinit var password_confirm: EditText
    private lateinit var first_name: EditText
    private lateinit var last_name: EditText
    private var db = Firebase.firestore
    lateinit var userID: String
    lateinit var firstName : String
    lateinit var lastName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_page)

        email = findViewById(R.id.EmailInput_register)
        password = findViewById(R.id.PasswordInput_register)
        btnRegister = findViewById(R.id.Registration_btn)
        password_confirm = findViewById(R.id.confirm_password)
        first_name = findViewById(R.id.FirstNameInput_register)
        last_name = findViewById(R.id.LastNameInput_register)



        btnRegister.setOnClickListener {
            //Create an intent to go to the login activity
            firstName = first_name.text.toString()
            lastName = last_name.text.toString()
            val userEmail = email.text.toString()
            val userPassword = password.text.toString()
            val confirmPassword = password_confirm.text.toString()
            if (userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            } else if (userPassword != confirmPassword){
                password.text.toString() != password_confirm.text.toString()
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                registerUser(userEmail, userPassword){
                    addUser(firstName, lastName)
                }

            }
        }
    }

    //Register user in firebase authentication
    private fun registerUser(useremail: String, userpassword: String, callback: () -> Unit) {
        //Create a firebase instance
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(useremail, userpassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //If the registration is successful, go to the login activity
                    val intent = Intent(this, Login::class.java)
                    intent.putExtra("firstName", firstName)
                    startActivity(intent)
                    finish()
                    callback()
                } else {
                    Log.e("RegistrationActivity", "Registration failed", task.exception)
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    //Add user first and last name to firestore database
    private fun addUser(firstName: String, lastName: String) {
        val user = hashMapOf(
            "firstName" to firstName,
            "lastName" to lastName,
        )
        userID = FirebaseAuth.getInstance().currentUser!!.uid

        db.collection("users").document(userID)
            .set(user)
            .addOnSuccessListener {
                Toast.makeText(this, "User added", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error adding user", Toast.LENGTH_SHORT).show()
            }
    }
}
