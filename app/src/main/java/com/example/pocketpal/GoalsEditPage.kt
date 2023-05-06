package com.example.pocketpal;

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class GoalsEditPage : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals_update_page)

        val database = Firebase.database
        val myRef = database.getReference("Goals")
        val userId = "user123"

        val key = intent.getStringExtra("key")
        val title = intent.getStringExtra("title")
        val duration = intent.getStringExtra("duration")
        val amount = intent.getStringExtra("amount")
        val targetDate = intent.getStringExtra("targetDate")


        val EditGoal = findViewById<EditText>(R.id.EditGoal)
        val editTextTextPersonName4 = findViewById<EditText>(R.id.editTextTextPersonName4)
        val editTextTextPersonName = findViewById<EditText>(R.id.editTextTextPersonName)
        val editTextDate2 = findViewById<EditText>(R.id.editTextDate2)
        val button10 = findViewById<Button>(R.id.button10)
        val button = findViewById<Button>(R.id.button)
        val button12 = findViewById<Button>(R.id.button12)

        EditGoal.setText(title)
        editTextTextPersonName4.setText(duration)
        editTextTextPersonName.setText(amount)
        editTextDate2.setText(targetDate)

        button10.setOnClickListener {
            if (EditGoal.text.isEmpty() || editTextTextPersonName4.text.isEmpty() || editTextTextPersonName.text.isEmpty() || editTextDate2.text.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val titleX = EditGoal.text.toString()
            val amountX = editTextTextPersonName4.text.toString().toDouble()
            val durationX = editTextTextPersonName.text.toString().toDouble().toInt()
            val targetDateX = editTextDate2.text.toString()


            val goal = Goal(titleX, amountX, durationX, targetDateX)
            if (userId != null) {
                if (key != null) {
                    myRef.child(userId).child(key).setValue(goal)
                }
            }
            Toast.makeText(this, "Successfully saved", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }

        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        button12.setOnClickListener{
            finish()
        }

    }

}