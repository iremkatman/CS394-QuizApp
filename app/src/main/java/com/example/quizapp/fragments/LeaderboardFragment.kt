package com.example.quizapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.R
import com.example.quizapp.adapters.LeaderboardAdapter
import com.example.quizapp.databinding.FragmentLeaderboardBinding
import com.example.quizapp.models.User

class LeaderboardFragment : Fragment() {
    private lateinit var binding: FragmentLeaderboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLeaderboardBinding.inflate(inflater, container, false)

        // Örnek kullanıcı listesi
        val userList = listOf(
            User("David", 1200),
            User("Ivan", 1500),
            User("Austin", 1100),
            User("Thomas", 1180),
            User("Keith", 1199)
        )

        // RecyclerView ve Adapter ayarları
        val adapter = LeaderboardAdapter(userList)
        binding.leaderboardRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.leaderboardRecyclerView.adapter = adapter

        return binding.root
    }
}