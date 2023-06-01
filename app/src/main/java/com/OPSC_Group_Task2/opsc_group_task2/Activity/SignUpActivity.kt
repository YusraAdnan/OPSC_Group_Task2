package com.OPSC_Group_Task2.opsc_group_task2.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.OPSC_Group_Task2.opsc_group_task2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    lateinit var etEmail: EditText
    lateinit var etUsername: EditText
    lateinit var etFullName: EditText
    lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_sign_up)
        super.onCreate(savedInstanceState)

        etEmail = findViewById(R.id.etEmail)
        etFullName = findViewById(R.id.etFullName)
        etUsername = findViewById(R.id.etUserName)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignUp = findViewById(R.id.btnSignup)

        firebaseAuth = Firebase.auth

        btnSignUp.setOnClickListener {
            UserSignUp()
        }
        // switching from signUp Activity to Login Activity

        btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun UserSignUp() {
        val email = etEmail.text.toString()
        val fullname = etFullName.text.toString()
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()
        val confirmPassword = etConfirmPassword.text.toString()

        // checks
        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Fill in you email and password", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(
                this,
                "Password and Confirm Password do not match",
                Toast.LENGTH_SHORT
            )
                .show()
            return
        }
        // If all credential are correct
        // We call createUserWithEmailAndPassword
        // using auth object and pass the
        // email and pass in it.
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {

                    Toast.makeText(this, "Signed in Successfully!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Sing Up Failed!", Toast.LENGTH_SHORT).show()
                }

            }


    }
}