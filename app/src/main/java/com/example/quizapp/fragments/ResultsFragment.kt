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

        binding.tvResult.text = "You answered $correctAnswers out of $totalQuestions correctly!"

        binding.btnRestart.setOnClickListener {
// ViewModel'i sıfırlayarak QuizFragment'e dön
            val navController = findNavController()
            navController.popBackStack(R.id.quizFragment, true) // QuizFragment'i yeniden başlatmak için önce stack'i temizle
            navController.navigate(R.id.quizFragment)        }

        binding.btnHome.setOnClickListener {
            findNavController().navigate(R.id.action_resultsFragment_to_homeFragment)
        }

        return binding.root
    }
}
