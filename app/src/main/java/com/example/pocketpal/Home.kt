package com.example.pocketpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Home : AppCompatActivity() {

    private lateinit var btnRegisterHome: Button
    private lateinit var btnLoginHome: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnRegisterHome = findViewById(R.id.btnRegisterHome)
        btnLoginHome = findViewById(R.id.btnLoginRegister)

        btnRegisterHome.setOnClickListener{
            val intent = Intent (this, Register:: class.java)
            startActivity(intent)
        }

        btnLoginHome.setOnClickListener{
            val intent = Intent (this, Login:: class.java)
            startActivity(intent)
        }
    }
}