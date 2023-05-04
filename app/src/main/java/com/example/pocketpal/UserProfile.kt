package com.example.pocketpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class UserProfile : AppCompatActivity() {

    private lateinit var btnLogOut: Button
    private lateinit var btnMyBudget: Button
    private lateinit var btnNewMonth: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val emailTextView = findViewById<TextView>(R.id.emailTextView)

        // Get current user's data
        val user = FirebaseAuth.getInstance().currentUser
        val email = user?.email

        // Set user data to TextViews
        emailTextView.text = email

        btnLogOut = findViewById(R.id.btnLogOut)
        btnMyBudget = findViewById(R.id.btnMyBudget)
        btnNewMonth = findViewById(R.id.btnNewMonth)

        btnLogOut.setOnClickListener{
            val intent = Intent (this, Home:: class.java)
            startActivity(intent)
        }

        btnMyBudget.setOnClickListener{
            val intent = Intent (this, FetchMonth:: class.java)
            startActivity(intent)
        }

        btnNewMonth.setOnClickListener{
            val intent = Intent (this, MainActivity:: class.java)
            startActivity(intent)
        }
    }
}