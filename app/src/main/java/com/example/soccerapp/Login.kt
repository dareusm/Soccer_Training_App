package com.example.soccerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.soccerapp.Registration
import com.example.soccerapp.MainActivity

class Login : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button

    private lateinit var auth: FirebaseAuth
    lateinit var firstName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        email = findViewById(R.id.emailLogin_Input)
        password = findViewById(R.id.passwordLogin_Input)
        btnLogin = findViewById(R.id.Login_btn)
        btnRegister = findViewById(R.id.Register_btn)

        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener{
            val userEmail = email.text.toString()
            val userPassword = password.text.toString()
            if(userEmail.isEmpty() || userPassword.isEmpty()){
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(userEmail, userPassword){
                    getUser()
                }
            }
        }

        btnRegister.setOnClickListener{
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(userEmail: String, userPassword: String, callback: () -> Unit) {
        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful && auth.currentUser != null) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    getUser()
                }
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
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("firstName", firstName)
                            startActivity(intent)
                            finish()
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