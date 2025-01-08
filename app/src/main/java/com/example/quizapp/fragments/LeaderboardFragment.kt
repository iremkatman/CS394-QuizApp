package com.example.quizapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.adapters.LeaderboardAdapter
import com.example.quizapp.databinding.FragmentLeaderboardBinding
import com.example.quizapp.models.User
import com.google.firebase.firestore.FirebaseFirestore

class LeaderboardFragment : Fragment() {

    private lateinit var binding: FragmentLeaderboardBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var leaderboardAdapter: LeaderboardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLeaderboardBinding.inflate(inflater, container, false)
        firestore = FirebaseFirestore.getInstance()

        // RecyclerView yapılandırması
        leaderboardAdapter = LeaderboardAdapter()
        binding.leaderboardRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.leaderboardRecyclerView.adapter = leaderboardAdapter

        // Firestore'dan verileri çek
        fetchLeaderboardData()

        return binding.root
    }

    private fun fetchLeaderboardData() {
        firestore.collection("Leaderboard")
            .orderBy("totalScore", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                val userList = result.map { document ->
                    val nickname = document.getString("nickname") ?: "Unknown"
                    val totalScore = document.getLong("totalScore")?.toInt() ?: 0
                    User(nickname, totalScore)
                }
                leaderboardAdapter.submitList(userList)
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Failed to fetch leaderboard data!", Toast.LENGTH_SHORT).show()
            }
    }
}
