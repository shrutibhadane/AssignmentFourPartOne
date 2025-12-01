package com.example.assignmentfourpartone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ScoreAdapter(private val scoreList: MutableList<ScoreModel>) :
    RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_score, parent, false)
        return ScoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val score = scoreList[position]
        holder.tvName.text = score.name
        holder.tvScore.text = score.score.toString()
        holder.tvDate.text = score.date
    }

    override fun getItemCount(): Int {
        return scoreList.size
    }

    class ScoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvScore: TextView = itemView.findViewById(R.id.tvScore)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
    }
}