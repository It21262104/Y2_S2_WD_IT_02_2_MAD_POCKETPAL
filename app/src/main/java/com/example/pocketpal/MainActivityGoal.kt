package com.example.pocketpal

import GoalAdapter
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivityGoal : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_goal)

        val database = Firebase.database
        val myRef = database.getReference("Goals")
        val userId = "user123" // add real user ID

        val tvFinance: TextView = findViewById(R.id.tvFinance)
        val textView5: TextView = findViewById(R.id.textView5)
        val recyclerViewGoals: RecyclerView = findViewById(R.id.recyclerViewGoals)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)

        myRef.child(userId).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val goals = mutableMapOf<String, Map<String, Any>>()
                for (goalSnapshot in snapshot.children) {
                    val goal = goalSnapshot.getValue(Goal::class.java)
                    goal?.let { goalData ->
                        val goalMap = mapOf(
                            "title" to goalData.title.toString(),
                            "amount" to goalData.amount.toString(),
                            "duration" to goalData.duration.toString(),
                            "targetDate" to goalData.targetDate.toString()
                        )
                        goals[goalSnapshot.key!!] = goalMap
                    }
                }
                val recyclerView: RecyclerView = findViewById(R.id.recyclerViewGoals)
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivityGoal)
                var adapter  = GoalAdapter(goals)
                recyclerView.adapter = adapter
                adapter.onItemClickListener = object : GoalAdapter.OnItemClickListener {
                    override fun onItemClick(
                        key: String,
                        title: String,
                        duration: String,
                        amount: String,
                        targetDate: String
                    ) {
                        val intent = Intent(this@MainActivityGoal, GoalsDetailPage::class.java)
                        // Put the data as extras
                        intent.putExtra("key", key)
                        intent.putExtra("title", title)
                        intent.putExtra("duration", duration)
                        intent.putExtra("amount", amount)
                        intent.putExtra("targetDate", targetDate)
                        startActivity(intent)
                    }
                }

                adapter.onDeleteClickListener = object : GoalAdapter.OnDeleteClickListener{
                    override fun onItemClick(key: String) {
                        myRef.child(userId).child(key).removeValue()
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

        button1.setOnClickListener {
            val intent = Intent(this, GoalsAddPage::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        button3.setOnClickListener {
            val intent = Intent(this, FinancialTipsPage::class.java)
            startActivity(intent)
        }
    }

}