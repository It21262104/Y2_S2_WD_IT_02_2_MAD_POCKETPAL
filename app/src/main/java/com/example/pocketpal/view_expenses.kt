package com.example.pocketpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketpal.adapters.CreditAdapter
import com.example.pocketpal.models.CreditModel
import com.google.firebase.database.*

class view_expenses : AppCompatActivity() {

    private lateinit var creditRecyclerView: RecyclerView
    private lateinit var tvLoading : TextView
    private lateinit var monthList: ArrayList<CreditModel>
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_expenses)

        creditRecyclerView = findViewById(R.id.rvCredit)
        tvLoading = findViewById(R.id.tvLoading)
        creditRecyclerView.layoutManager = LinearLayoutManager(this)
        creditRecyclerView.setHasFixedSize(true)

        monthList = arrayListOf<CreditModel>()

        getCreditData()
    }

    private fun getCreditData(){
        creditRecyclerView.visibility = View.GONE
        tvLoading.visibility = View.VISIBLE

         dbRef = FirebaseDatabase.getInstance().getReference("Credits")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                monthList.clear()
                if(snapshot.exists()){
                    for(monthSnap in snapshot.children){
                        val creditData = monthSnap.getValue(CreditModel::class.java)
                        monthList.add(creditData!!)
                    }

                    val cAdapter = CreditAdapter(monthList)
                    creditRecyclerView.adapter = cAdapter

                    cAdapter.setOnItemClickListener(object : CreditAdapter.onItemClickListner{
                        override fun onItemClick(position: Int) {
                            intent = Intent(this@view_expenses,view_per_month::class.java)

                            //put Extras
                            intent.putExtra("exId", monthList[position].exId)
                            intent.putExtra("month", monthList[position].month)
                            intent.putExtra("amount", monthList[position].amount)

                            intent.putExtra("purpose1", monthList[position].purpose1)
                            intent.putExtra("purpose2", monthList[position].purpose2)
                            intent.putExtra("purpose3", monthList[position].purpose3)
                            intent.putExtra("purpose4", monthList[position].purpose4)

                            intent.putExtra("amount1", monthList[position].Amount1)
                            intent.putExtra("amount2", monthList[position].Amount2)
                            intent.putExtra("amount3", monthList[position].Amount3)
                            intent.putExtra("amount4", monthList[position].Amount4)

                            startActivity(intent)
                        }

                    })

                    creditRecyclerView.visibility = View.VISIBLE
                    tvLoading.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}