package com.example.pocketpal;

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FinancialTipsPage : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_financial_tips_page)

        val button: Button = findViewById(R.id.button11)


        button.setOnClickListener {
            finish()
        }



    }

}
