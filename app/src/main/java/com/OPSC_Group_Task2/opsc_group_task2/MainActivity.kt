package com.OPSC_Group_Task2.opsc_group_task2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    lateinit var loginButton: Button
    lateinit var signupButton: Button

    lateinit var firebase: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton = findViewById(R.id.btnLogin)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        signupButton = findViewById(R.id.btnSignup)

        firebase = Firebase.auth

        loginButton.setOnClickListener {
            login()
        }

        signupButton.setOnClickListener(){

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
    private fun login() {
        val email = etEmail.text.toString()
        val pass = etPassword.text.toString()
        // calling signInWithEmailAndPassword(email, pass)
        // function using Firebase auth object
        // On successful response Display a Toast
        firebase.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Login Succeeded", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomePageActivity::class.java)
                startActivity(intent)
            } else
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
        }
    }

    }
