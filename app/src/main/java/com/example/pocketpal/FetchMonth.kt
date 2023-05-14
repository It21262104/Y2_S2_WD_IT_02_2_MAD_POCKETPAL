package com.example.pocketpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketpal.adapters.BudgetAdapter
import com.example.pocketpal.models.BudgetModel
import com.google.firebase.database.*

class FetchMonth : AppCompatActivity() {

    private lateinit var monthRecycleView: RecyclerView
    private lateinit var tvIsLoadingData: TextView
    private lateinit var budgetList: ArrayList<BudgetModel>

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_month)

        monthRecycleView = findViewById(R.id.rvMonth)
        monthRecycleView.layoutManager = LinearLayoutManager(this)
        monthRecycleView.setHasFixedSize(true)
        tvIsLoadingData = findViewById(R.id.tvLoadingData)

        budgetList = arrayListOf<BudgetModel>()

        getBudgetData()
    }

    private fun getBudgetData() {
        monthRecycleView.visibility = View.GONE
        tvIsLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Budget")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                budgetList.clear()

                if (snapshot.exists()) {
                    for (monthSnap in snapshot.children) {
                        val budgetData = monthSnap.getValue(BudgetModel::class.java)
                        budgetList.add(budgetData!!)
                    }

                    val mAdapter = BudgetAdapter(budgetList)
                    monthRecycleView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : BudgetAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@FetchMonth, ViewCategories::class.java)

                            //put extras
                            intent.putExtra("budgetId", budgetList[position].budgetId)
                            intent.putExtra("month", budgetList[position].month)
                            intent.putExtra("salary", budgetList[position].salary)
                            intent.putExtra("percentage1", budgetList[position].percentage1)
                            intent.putExtra("percentage2", budgetList[position].percentage2)
                            intent.putExtra("percentage3", budgetList[position].percentage3)
                            intent.putExtra("percentage4", budgetList[position].percentage14)
                            intent.putExtra("percentage5", budgetList[position].percentage5)
                            startActivity(intent)
                        }

                    })

                    monthRecycleView.visibility = View.VISIBLE
                    tvIsLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}