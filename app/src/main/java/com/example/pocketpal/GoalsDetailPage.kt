package com.example.pocketpal;

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToInt

class GoalsDetailPage : AppCompatActivity(){

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_detail)

        // Get the data from the extras
        val key = intent.getStringExtra("key")
        val title = intent.getStringExtra("title")
        val duration = intent.getStringExtra("duration")
        val amount = intent.getStringExtra("amount")
        val targetDate = intent.getStringExtra("targetDate")


        val tvFinance = findViewById<TextView>(R.id.tvFinance)
        val textView2 = findViewById<TextView>(R.id.textView2)
        val textView9 = findViewById<TextView>(R.id.textView9)
        val textView10 = findViewById<TextView>(R.id.textView10)
        val textView11 = findViewById<TextView>(R.id.textView11)
        val textView12 = findViewById<TextView>(R.id.textView12)
        val tvCalculation = findViewById<TextView>(R.id.tvCalculation)
        val button2 = findViewById<Button>(R.id.button2)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)

        textView9.text = title
        textView10.text = "Goal Amount Rs.$amount"
        textView11.text = "Duration $duration months"
        textView12.text = "Start date $targetDate"
        tvCalculation.text = "Savings needed per month : "

        button2.setOnClickListener{
            val am = amount?.toDouble()
            val dur = duration?.toInt()
            var fin = am!! / dur!!
            fin = fin.roundToInt().toDouble()
            tvCalculation.text = "Savings needed per month : $fin"
        }
        button4.setOnClickListener{
            val intent = Intent(this@GoalsDetailPage, GoalsEditPage::class.java)
            intent.putExtra("key", key)
            intent.putExtra("title", title)
            intent.putExtra("duration", duration)
            intent.putExtra("amount", amount)
            intent.putExtra("targetDate", targetDate)
            startActivity(intent)
        }
        button5.setOnClickListener{
            finish()
        }

    }
    fun calculateSavingsNeededPerMonth(amount: Int, duration: Int?): Double {
        val savingsPerMonth = amount.toDouble() / (duration ?: 1)
        return savingsPerMonth.roundToInt().toDouble()
    }


}
