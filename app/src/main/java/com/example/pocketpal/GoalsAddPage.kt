package com.example.pocketpal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


data class Goal @JvmOverloads constructor(
    var title: String? = null,
    var amount: Double? = null,
    var duration: Int? = null,
    var targetDate: String? = null
)


class GoalsAddPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals_add_page)

        val database = Firebase.database
        val myRef = database.getReference("Goals")
        val userId = intent.getStringExtra("userId")



// val tvFinance: TextView = findViewById(R.id.tvFinance)
// val tvGoal: TextView = findViewById(R.id.tvGoal)
        val editTextTitle: EditText = findViewById(R.id.editTextTitle)
// val textView6: TextView = findViewById(R.id.textView6)
        val editTextAmount: EditText = findViewById(R.id.editTextAmount)
// val textView8: TextView = findViewById(R.id.textView8)
        val editTextDuration: EditText = findViewById(R.id.editTextDuration)
// val textView4: TextView = findViewById(R.id.textView4)
        val editTextTargetDate: EditText = findViewById(R.id.editTextTargetDate)
        val button10: Button = findViewById(R.id.button10)
        val button: Button = findViewById(R.id.button)
        val button12: Button = findViewById(R.id.button12)

        button10.setOnClickListener{


            if (editTextTitle.text.isEmpty() || editTextAmount.text.isEmpty() || editTextDuration.text.isEmpty() || editTextTargetDate.text.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val title = editTextTitle.text.toString()
            val targetDate = editTextTargetDate.text.toString()

            val amount: Double
            try {
                amount = editTextAmount.text.toString().toDouble()
            } catch (e: NumberFormatException) {
                editTextAmount.error = "Invalid amount"
                return@setOnClickListener
            }

            val duration: Int
            try {
                duration = editTextDuration.text.toString().toInt()
            } catch (e: NumberFormatException) {
                editTextDuration.error = "Invalid duration"
                return@setOnClickListener
            }

            val pattern = Regex("^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/([0-9]{4})\$")
            val result = pattern.containsMatchIn(targetDate)
            if (!result) {
                editTextTargetDate.error = "Invalid date (dd/mm/yyyy)"
                return@setOnClickListener
            }


            val goal = Goal(title, amount, duration, targetDate)
            if (userId != null) {
                myRef.child(userId).push().setValue(goal)
            }

            editTextTitle.setText("")
            editTextAmount.setText("")
            editTextDuration.setText("")
            editTextTargetDate.setText("")
            Toast.makeText(this, "Successfully Added the Goal", Toast.LENGTH_SHORT).show()
            finish()
        }

        button12.setOnClickListener {
            finish()
        }

        button.setOnClickListener {
            val intent = Intent(this, MainActivityGoal::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }



    }
}