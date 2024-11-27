package com.example.quizapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.databinding.ItemLeaderboardBinding
import com.example.quizapp.models.User

class LeaderboardAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder>() {

    inner class LeaderboardViewHolder(val binding: ItemLeaderboardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val binding = ItemLeaderboardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return LeaderboardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        val user = userList[position]
        holder.binding.tvUserName.text = user.name
        holder.binding.tvUserScore.text = user.score.toString()
    }

    override fun getItemCount(): Int = userList.size
}