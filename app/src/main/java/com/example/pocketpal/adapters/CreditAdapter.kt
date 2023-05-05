package com.example.pocketpal.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketpal.R
import com.example.pocketpal.models.CreditModel

class CreditAdapter(private val monthList : List<CreditModel>) : RecyclerView.Adapter<CreditAdapter.ViewHolder>(){

    private lateinit var mListner : onItemClickListner

    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListner){
        mListner = clickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.month_list, parent, false)
        return ViewHolder(itemView, mListner)
    }

    override fun onBindViewHolder(holder: CreditAdapter.ViewHolder, position: Int) {
          val currentMonth = monthList[position]
        holder.tvMonth.text = currentMonth.month
    }

    override fun getItemCount(): Int {
        return monthList.size
    }

    class ViewHolder(view: View, clickListener: onItemClickListner) : RecyclerView.ViewHolder(view) {
        val tvMonth : TextView = itemView.findViewById(R.id.tvMonthName)

        init{
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

}