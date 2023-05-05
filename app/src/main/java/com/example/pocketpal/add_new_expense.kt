package com.example.pocketpal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pocketpal.models.CreditModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class add_new_expense : AppCompatActivity() {

    private lateinit var edtMonth : EditText
    private lateinit var edtPurpose1 : EditText
    private lateinit var edtPurpose2 : EditText
    private lateinit var edtPurpose3 : EditText
    private lateinit var edtPurpose4 : EditText
    private lateinit var edtExAmount : EditText
    private lateinit var edtAmount1 : EditText
    private lateinit var edtAmount2 : EditText
    private lateinit var edtAmount3 : EditText
    private lateinit var edtAmount4 : EditText
    private lateinit var btnAddData : Button

    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_expense)

        edtMonth = findViewById(R.id.edtUpMonth)
        edtPurpose1 = findViewById(R.id.tvShowP1)
        edtPurpose2 = findViewById(R.id.tvShowP2)
        edtPurpose3 = findViewById(R.id.tvShowP3)
        edtPurpose4 = findViewById(R.id.tvShowP4)
        edtExAmount = findViewById(R.id.tvShowAmount)
        edtAmount1 = findViewById(R.id.tvShowAm1)
        edtAmount2 = findViewById(R.id.tvShowAm2)
        edtAmount3 = findViewById(R.id.tvShowAm3)
        edtAmount4 = findViewById(R.id.tvShowAm4)
        btnAddData = findViewById(R.id.btnSaveUpdaate)

        dbRef = FirebaseDatabase.getInstance().getReference("Credits")

        btnAddData.setOnClickListener{
            saveCreditData()
        }
    }

   private fun saveCreditData(){
        //getting values
        val month = edtMonth.text.toString()
        val purpose1 = edtPurpose1.text.toString()
        val purpose2 = edtPurpose2.text.toString()
        val purpose3 = edtPurpose3.text.toString()
        val purpose4 = edtPurpose4.text.toString()
        val amount1 = edtAmount1.text.toString()
        val amount2 = edtAmount2.text.toString()
        val amount3 = edtAmount3.text.toString()
        val amount4 = edtAmount4.text.toString()
        val amount = edtExAmount.text.toString()

        //validating the form
        if(month.isEmpty()){
            edtMonth.error="Please enter the  month"
        }

        if(amount.isEmpty()){
            edtExAmount.error="Please enter the amount"
        }

        //creating a unique ID
        val exID = dbRef.push().key!!

        val  credit = CreditModel(exID, month, amount, purpose1, purpose2, purpose3, purpose4, amount1, amount2, amount3, amount4)

        dbRef.child(exID).setValue(credit)
            .addOnCompleteListener{
                Toast.makeText(this,"Data Inserted Succesfully", Toast.LENGTH_LONG).show()

                edtMonth.text.clear()
                edtPurpose1.text.clear()
                edtPurpose2.text.clear()
                edtPurpose3.text.clear()
                edtPurpose4.text.clear()
                edtAmount1.text.clear()
                edtAmount2.text.clear()
                edtAmount3.text.clear()
                edtAmount4.text.clear()
                edtExAmount.text.clear()
            }.addOnFailureListener{
                err -> Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
}