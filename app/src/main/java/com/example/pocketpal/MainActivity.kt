package com.example.pocketpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pocketpal.models.BudgetModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var edtMonthMain: EditText
    private lateinit var edtSalaryMain: EditText
    private lateinit var edtPercentage1: EditText
    private lateinit var edtPercentage2: EditText
    private lateinit var edtPercentage3: EditText
    private lateinit var edtPercentage4: EditText
    private lateinit var edtPercentage5: EditText
    private lateinit var btnSaveMain: Button
    private lateinit var btnViewBudget: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtMonthMain = findViewById(R.id.edtMonthMain)
        edtSalaryMain = findViewById(R.id.edtSalaryMain)
        edtPercentage1 = findViewById(R.id.edtPercentage1)
        edtPercentage2 = findViewById(R.id.edtPercentage2)
        edtPercentage3 = findViewById(R.id.edtPercentage3)
        edtPercentage4 = findViewById(R.id.edtPercentage4)
        edtPercentage5 = findViewById(R.id.edtPercentage5)
        btnSaveMain = findViewById(R.id.btnSaveMain)
        btnViewBudget = findViewById(R.id.btnViewBudget)

        dbRef = FirebaseDatabase.getInstance().getReference("Budget")

        btnSaveMain.setOnClickListener() {
            saveBudget()
        }

        btnViewBudget.setOnClickListener{
            val intent = Intent (this, FetchMonth:: class.java)
            startActivity(intent)
        }
    }

    private fun saveBudget() {

        //getting values
        val month = edtMonthMain.text.toString()
        val salary = edtSalaryMain.text.toString()
        val percentage1 = edtPercentage1.text.toString()
        val percentage2 = edtPercentage2.text.toString()
        val percentage3 = edtPercentage3.text.toString()
        val percentage4 = edtPercentage4.text.toString()
        val percentage5 = edtPercentage5.text.toString()

        //adding validations
        if (month.isEmpty()) {
            edtMonthMain.error = "Please enter month"
        }
        if (salary.isEmpty()) {
            edtSalaryMain.error = "Please enter salary"
        }

        //creating a unique id to avoid from overriding data
        val budgetId = dbRef.push().key!!

        val budg = BudgetModel(budgetId, month, salary, percentage1, percentage2, percentage3, percentage4, percentage5)

        dbRef.child(budgetId).setValue(budg).addOnCompleteListener{
            Toast.makeText(this, "Data inserted successfuly", Toast.LENGTH_LONG).show()

            //clear data once inserted
            edtMonthMain.text.clear()
            edtSalaryMain.text.clear()
            edtPercentage1.text.clear()
            edtPercentage2.text.clear()
            edtPercentage3.text.clear()
            edtPercentage4.text.clear()
            edtPercentage5.text.clear()

        }.addOnFailureListener{ err ->
            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
        }
    }
}