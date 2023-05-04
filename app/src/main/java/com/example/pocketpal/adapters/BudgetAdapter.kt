package com.example.pocketpal.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketpal.R
import com.example.pocketpal.models.BudgetModel

class BudgetAdapter(private val budgetList: List<BudgetModel>) : RecyclerView.Adapter<BudgetAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    //to create the interface
    interface onItemClickListener{
        fun onItemClick (position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener) {
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.month_list_item, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMonth = budgetList[position]
        holder.tvMonth.text = currentMonth.month
    }

    override fun getItemCount(): Int {
        return budgetList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val tvMonth: TextView = itemView.findViewById(R.id.tvMonth)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}