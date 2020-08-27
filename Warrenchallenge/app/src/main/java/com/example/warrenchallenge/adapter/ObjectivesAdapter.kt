package com.example.warrenchallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.warrenchallenge.R
import com.example.warrenchallenge.model.Objective
import kotlinx.android.synthetic.main.row_objective.view.*

class ObjectivesAdapter(
    private val items: List<Objective>,
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_objective, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = items[position]
        with(holder as MyViewHolder) {
            tvObjectiveName.text = model.name
            tvObjectiveAmount.text = model.totalBalance.toString()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvObjectiveName = itemView.tv_objective_name
        val tvObjectiveAmount = itemView.tv_objective_amount
    }
}