package com.example.quizapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.models.User

class LeaderboardAdapter : RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder>() {

    private var userList = listOf<User>()

    fun submitList(list: List<User>) {
        userList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_leaderboard, parent, false)
        return LeaderboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user, position)
    }

    override fun getItemCount(): Int = userList.size

    class LeaderboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nicknameTextView: TextView = itemView.findViewById(R.id.tvNickname)
        private val scoreTextView: TextView = itemView.findViewById(R.id.tvScore)
        private val positionTextView: TextView = itemView.findViewById(R.id.tvPosition)

        fun bind(user: User, position: Int) {
            positionTextView.text = (position + 1).toString() // Sıra numarası
            nicknameTextView.text = user.name // Kullanıcı ismi
            scoreTextView.text = user.score.toString() // Kullanıcı skoru
        }
    }
}
