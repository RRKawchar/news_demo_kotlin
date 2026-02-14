package com.example.news_app_kotlin.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.news_app_kotlin.R
import com.example.news_app_kotlin.ui.main.MainActivity
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    private lateinit var etEmail:EditText
    private lateinit var etPassword:TextInputEditText
    private lateinit var loginBtn:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        etEmail=findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)
        loginBtn=findViewById(R.id.btnLogin)

        loginBtn.setOnClickListener{
            loginUser()
        }


    }

    private fun loginUser(){
        val email=etEmail.text.toString().trim()
        val password=etPassword.text.toString().trim()
        if(email.isEmpty()){   
            etEmail.error="Email required"
            return
        }

        if(password.isEmpty()){
            etPassword.error="Password required"
            return
        }
        Toast.makeText(this,"Login Successfully",Toast.LENGTH_SHORT).show()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        // Optional: close login screen so user can't go back
        finish()
    }
}