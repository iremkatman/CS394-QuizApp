package com.example.quizapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentResultsBinding

class ResultsFragment : Fragment() {

    private lateinit var binding: FragmentResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultsBinding.inflate(inflater, container, false)

        val correctAnswers = arguments?.getInt("correctAnswers") ?: 0
        val totalQuestions = arguments?.getInt("totalQuestions") ?: 0

        // Sonuç metnini ayarlama
        binding.tvResult.text = "You earned $correctAnswers  points"

        // "Show Leaderboard" butonu
        binding.btnRestart.text = "Show Leaderboard"
        binding.btnRestart.setOnClickListener {
            findNavController().navigate(R.id.action_resultsFragment_to_leaderboardFragment)
        }

        // "Go to Home" butonu
        binding.btnHome.setOnClickListener {
            findNavController().navigate(R.id.action_resultsFragment_to_homeFragment)
        }

        return binding.root
    }
}
