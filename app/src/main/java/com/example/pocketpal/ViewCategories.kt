package com.example.pocketpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.pocketpal.models.BudgetModel
import com.google.firebase.database.FirebaseDatabase

class ViewCategories : AppCompatActivity() {

    //private lateinit var tvId: TextView
    private lateinit var tvMonth: TextView
    private lateinit var tvSalary: TextView
    private lateinit var tvPercentage1: TextView
    private lateinit var tvPercentage2: TextView
    private lateinit var tvPercentage3: TextView
    private lateinit var tvPercentage4: TextView
    private lateinit var tvPercentage5: TextView

    private lateinit var tvCal1: TextView
    private lateinit var tvCal2: TextView
    private lateinit var tvCal3: TextView
    private lateinit var tvCal4: TextView
    private lateinit var tvCal5: TextView

    private lateinit var tvTotal: TextView
    private lateinit var tvRemaining: TextView

    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var btnAddNewMonthViewCategory: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_categories)

        initView()
        setValuesToViews()
        calculateTotal()
        calculateRemaining()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("budgetId").toString(),
                intent.getStringExtra("month").toString()
            )
        }

        btnAddNewMonthViewCategory.setOnClickListener{
            val intent = Intent (this, MainActivity:: class.java)
            startActivity(intent)
        }

        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("budgetId").toString()
            )
        }
    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Budget").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, FetchMonth::class.java)
            finish()
            startActivity(intent)

        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Error ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        //tvId = findViewById(R.id.tvId)
        tvMonth = findViewById(R.id.tvMonth)
        tvSalary = findViewById(R.id.tvSalaryCalculation)
        tvPercentage1 = findViewById(R.id.tvPercentage1)
        tvPercentage2 = findViewById(R.id.tvPercentage2)
        tvPercentage3 = findViewById(R.id.tvPercentage3)
        tvPercentage4 = findViewById(R.id.tvPercentage4)
        tvPercentage5 = findViewById(R.id.tvPercentage5)

        tvCal1 = findViewById(R.id.tvCal1)
        tvCal2 = findViewById(R.id.tvCal2)
        tvCal3 = findViewById(R.id.tvCal3)
        tvCal4 = findViewById(R.id.tvCal4)
        tvCal5 = findViewById(R.id.tvCal5)

        tvTotal = findViewById(R.id.tvTotal)
        tvRemaining = findViewById(R.id.tvRemaining)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
        btnAddNewMonthViewCategory = findViewById(R.id.btnAddNewMonthViewCategory)
    }

    private fun setValuesToViews() {

        //tvId.text = intent.getStringExtra("budgetId")
        tvMonth.text = intent.getStringExtra("month")
        tvSalary.text = intent.getStringExtra("salary")

        val percentage1 = intent.getStringExtra("percentage1")?.toDoubleOrNull() ?: 0.0
        val percentage2 = intent.getStringExtra("percentage2")?.toDoubleOrNull() ?: 0.0
        val percentage3 = intent.getStringExtra("percentage3")?.toDoubleOrNull() ?: 0.0
        val percentage4 = intent.getStringExtra("percentage4")?.toDoubleOrNull() ?: 0.0
        val percentage5 = intent.getStringExtra("percentage5")?.toDoubleOrNull() ?: 0.0
        val salary = intent.getStringExtra("salary")?.toDoubleOrNull() ?: 0.0

        tvPercentage1.text = String.format("%.2f%%", percentage1)
        tvPercentage2.text = String.format("%.2f%%", percentage2)
        tvPercentage3.text = String.format("%.2f%%", percentage3)
        tvPercentage4.text = String.format("%.2f%%", percentage4)
        tvPercentage5.text = String.format("%.2f%%", percentage5)

        val calculated1 = salary * (percentage1 / 100)
        val calculated2 = salary * (percentage2 / 100)
        val calculated3 = salary * (percentage3 / 100)
        val calculated4 = salary * (percentage4 / 100)
        val calculated5 = salary * (percentage5 / 100)

        tvCal1.text = String.format("%.2f", calculated1)
        tvCal2.text = String.format("%.2f", calculated2)
        tvCal3.text = String.format("%.2f", calculated3)
        tvCal4.text = String.format("%.2f", calculated4)
        tvCal5.text = String.format("%.2f", calculated5)
    }

    private fun calculateTotal() {
        val total = tvCal1.text.toString().toDouble() +
                tvCal2.text.toString().toDouble() +
                tvCal3.text.toString().toDouble() +
                tvCal4.text.toString().toDouble() +
                tvCal5.text.toString().toDouble()
        tvTotal.text = total.toString()
    }

    private fun calculateRemaining() {
        val remaining = tvSalary.text.toString().toDouble() - tvTotal.text.toString().toDouble()
        tvRemaining.text = remaining.toString()
    }

    private fun openUpdateDialog(
        budgetId: String,
        month: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        //set the view to dialog
        mDialog.setView(mDialogView)

        //initializing views
        val month = mDialogView.findViewById<EditText>(R.id.edtMonthUpdate)
        val salary = mDialogView.findViewById<EditText>(R.id.edtSalaryUpdate)
        val percentage1 = mDialogView.findViewById<EditText>(R.id.edtpercentage1Update)
        val percentage2 = mDialogView.findViewById<EditText>(R.id.edtPercentage2Update)
        val percentage3 = mDialogView.findViewById<EditText>(R.id.edtPercentage3Update)
        val percentage4 = mDialogView.findViewById<EditText>(R.id.edtpercentage4Update)
        val percentage5 = mDialogView.findViewById<EditText>(R.id.edtPercentage5Update)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        month.setText(intent.getStringExtra("month").toString())
        salary.setText(intent.getStringExtra("salary").toString())
        percentage1.setText(intent.getStringExtra("percentage1").toString())
        percentage2.setText(intent.getStringExtra("percentage2").toString())
        percentage3.setText(intent.getStringExtra("percentage3").toString())
        percentage4.setText(intent.getStringExtra("percentage4").toString())
        percentage5.setText(intent.getStringExtra("percentage5").toString())

        //mDialog.setTitle("Updating $month Record")

        //create dialog
        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener{
            updateMonthData(
                budgetId,
                month.text.toString(),
                salary.text.toString(),
                percentage1.text.toString(),
                percentage2.text.toString(),
                percentage3.text.toString(),
                percentage4.text.toString(),
                percentage5.text.toString()
            )

            Toast.makeText(applicationContext, "Data Updated", Toast.LENGTH_LONG).show()

            //setting updated data to the text views
            tvMonth.text = month.text.toString()
            tvSalary.text = salary.text.toString()
            tvPercentage1.text = percentage1.text.toString()
            tvPercentage2.text = percentage2.text.toString()
            tvPercentage3.text = percentage3.text.toString()
            tvPercentage4.text = percentage4.text.toString()
            tvPercentage5.text = percentage5.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updateMonthData(
        id: String,
        month: String,
        salary: String,
        per1: String,
        per2: String,
        per3: String,
        per4: String,
        per5: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Budget").child(id)
        val monthInfo = BudgetModel(id, month, salary, per1, per2, per3, per4, per5)
        dbRef.setValue(monthInfo)
    }
}