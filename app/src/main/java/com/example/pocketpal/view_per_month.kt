package com.example.pocketpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.pocketpal.models.CreditModel
import com.google.firebase.database.FirebaseDatabase

class view_per_month : AppCompatActivity() {
    private lateinit var tvShowMonth : TextView
    lateinit var tvShowAmount : TextView

    private lateinit var purpose1 : TextView
    private lateinit var purpose2 : TextView
    private lateinit var purpose3 : TextView
    private lateinit var purpose4 : TextView

    lateinit var amount1 : TextView
    lateinit var amount2 : TextView
    lateinit var amount3 : TextView
    lateinit var amount4 : TextView

    lateinit var totalEx : TextView
    lateinit var RemCredit : TextView

    private lateinit var btnUpdate : Button
    private lateinit var btnDelete : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_per_month)

        initView()
        setValuestoViews()
        calculateTotalExpense()
        calculataRemainingCredits()

        btnUpdate.setOnClickListener{
            openUpdateDialog(

                intent.getStringExtra("exId").toString(),
                intent.getStringExtra("month").toString()
            )
        }

        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("exId").toString()
            )
        }
    }

    fun calculataRemainingCredits(){
        val remaining = tvShowAmount.text.toString().toDouble() - totalEx.text.toString().toDouble()
        RemCredit.text = remaining.toString()
    }

    fun calculateTotalExpense() {
        val amount1Value = if (amount1.text.toString().isEmpty()) 0.0 else amount1.text.toString().toDouble()
        val amount2Value = if (amount2.text.toString().isEmpty()) 0.0 else amount2.text.toString().toDouble()
        val amount3Value = if (amount3.text.toString().isEmpty()) 0.0 else amount3.text.toString().toDouble()
        val amount4Value = if (amount4.text.toString().isEmpty()) 0.0 else amount4.text.toString().toDouble()

        val total = amount1Value + amount2Value + amount3Value + amount4Value

        totalEx.text = total.toString()
    }


    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Credits").child(id)
        val cTask = dbRef.removeValue()

        cTask.addOnSuccessListener {
            Toast.makeText(this, "Credit Data Deleted", Toast.LENGTH_LONG).show()
            val intent = Intent(this,view_expenses::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{
            error -> Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView(){
        tvShowMonth= findViewById(R.id.tvShowMonth)
        tvShowAmount = findViewById(R.id.tvShowAmount)

        purpose1 = findViewById(R.id.tvShowP1)
        purpose2 = findViewById(R.id.tvShowP2)
        purpose3 = findViewById(R.id.tvShowP3)
        purpose4 = findViewById(R.id.tvShowP4)

        amount1 = findViewById(R.id.tvShowAm1)
        amount2 = findViewById(R.id.tvShowAm2)
        amount3 = findViewById(R.id.tvShowAm3)
        amount4=findViewById(R.id.tvShowAm4)

        totalEx = findViewById(R.id.tvShowTotEx)
        RemCredit=findViewById(R.id.tvShowRem)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuestoViews(){
        tvShowMonth.text = intent.getStringExtra("month")
        tvShowAmount.text = intent.getStringExtra("amount")

        purpose1.text = intent.getStringExtra("purpose1")
        purpose2.text = intent.getStringExtra("purpose2")
        purpose3.text = intent.getStringExtra("purpose3")
        purpose4.text = intent.getStringExtra("purpose4")

        amount1.text = intent.getStringExtra("amount1")
        amount2.text = intent.getStringExtra("amount2")
        amount3.text = intent.getStringExtra("amount3")
        amount4.text = intent.getStringExtra("amount4")
    }

    private fun openUpdateDialog(
        exId : String,
        month:String
    ){
        val cDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val cDialogView = inflater.inflate(R.layout.update_credits,null)

        cDialog.setView(cDialogView)

        //initializing views
        val edtMonth = cDialogView.findViewById<EditText>(R.id.edtUpMonth)
        val edtAmount = cDialogView.findViewById<EditText>(R.id.edtUpAmount)
        val edtpurpose1 = cDialogView.findViewById<EditText>(R.id.edtUpP1)
        val edtpurpose2 = cDialogView.findViewById<EditText>(R.id.edtUpP2)
        val edtpurpose3 = cDialogView.findViewById<EditText>(R.id.tedtUpP3)
        val edtpurpose4 = cDialogView.findViewById<EditText>(R.id.edtUpP4)

        val edtAmount1 = cDialogView.findViewById<EditText>(R.id.edtUpAm1)
        val edtAmount2 = cDialogView.findViewById<EditText>(R.id.edtUpAm2)
        val edtAmount3 = cDialogView.findViewById<EditText>(R.id.edtUpAm3)
        val edtAmount4 = cDialogView.findViewById<EditText>(R.id.edtUpAm4)

        val btnSaveData = cDialogView.findViewById<Button>(R.id.btnSaveUpdaate)


        edtMonth.setText(intent.getStringExtra("month").toString())
        edtAmount.setText(intent.getStringExtra("amount").toString())

        edtpurpose1.setText(intent.getStringExtra("purpose1").toString())
        edtpurpose2.setText(intent.getStringExtra("purpose2").toString())
        edtpurpose3.setText(intent.getStringExtra("purpose3").toString())
        edtpurpose4.setText(intent.getStringExtra("purpose4").toString())

        edtAmount1.setText(intent.getStringExtra("amount1").toString())
        edtAmount2.setText(intent.getStringExtra("amount2").toString())
        edtAmount3.setText(intent.getStringExtra("amount3").toString())
        edtAmount4.setText(intent.getStringExtra("amount4").toString())

        cDialog.setTitle("Updating $month Record")

        val alertDialog = cDialog.create()
        alertDialog.show()

        btnSaveData.setOnClickListener{
            updateCreditData(
                exId,
                edtMonth.text.toString(),
                edtAmount.text.toString(),
                edtpurpose1.text.toString(), edtpurpose2.text.toString(), edtpurpose3.text.toString(), edtpurpose4.text.toString(),
                edtAmount1.text.toString(), edtAmount2.text.toString(), edtAmount3.text.toString(), edtAmount4.text.toString()
            )
            Toast.makeText(applicationContext, "Credit Data Updated", Toast.LENGTH_LONG).show()

            //setting Updated data to text views
            tvShowMonth.text = edtMonth.text.toString()
            tvShowAmount.text = edtAmount.text.toString()
            purpose1.text = edtpurpose1.text.toString()
            purpose2.text = edtpurpose2.text.toString()
            purpose3.text = edtpurpose3.text.toString()
            purpose4.text = edtpurpose4.text.toString()

            amount1.text = edtAmount1.text.toString()
            amount2.text = edtAmount2.text.toString()
            amount3.text = edtAmount3.text.toString()
            amount4.text = edtAmount4.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updateCreditData(
        id:String,month:String, amount:String, purpose1:String, purpose2:String, purpose3:String, purpose4:String,
        amount1:String, amount2:String, amount3:String, amount4:String
    ){

        val dbRef = FirebaseDatabase.getInstance().getReference("Credits").child(id)
        val creditInfo = CreditModel(id,month, amount, purpose1, purpose2,purpose3,purpose4,amount1,amount2,amount3,amount4)
        dbRef.setValue(creditInfo)
    }
}