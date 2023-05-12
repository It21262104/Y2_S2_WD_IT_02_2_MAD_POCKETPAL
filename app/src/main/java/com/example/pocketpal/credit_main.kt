package com.example.pocketpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class credit_main : AppCompatActivity() {

    private lateinit var btnAddNewExpense: Button
    private lateinit var btnMyExpenses : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.credit_main)

        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        btnAddNewExpense = findViewById(R.id.btnAddNewExpense)
        btnMyExpenses=findViewById(R.id.btnMyExpenses)

        btnAddNewExpense.setOnClickListener{
            val intent = Intent(this, add_new_expense::class.java)
            startActivity(intent)
        }

        btnMyExpenses.setOnClickListener{
            val intent = Intent(this, view_expenses::class.java)
            startActivity(intent)
        }



    }
}