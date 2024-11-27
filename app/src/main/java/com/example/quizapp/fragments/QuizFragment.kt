package com.example.quizapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.quizapp.databinding.FragmentQuizBinding
import com.example.quizapp.viewmodels.QuizViewModel
import com.google.android.material.snackbar.Snackbar

class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private val viewModel: QuizViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        setupObservers()

        binding.btnNext.setOnClickListener {
            viewModel.nextQuestion()
        }

        return binding.root
    }

    private fun setupObservers() {
        viewModel.currentQuestion.observe(viewLifecycleOwner) { question ->
            if (question != null) {
                binding.tvQuestion.text = question.text

                val optionButtons = listOf(binding.btnOption1, binding.btnOption2, binding.btnOption3, binding.btnOption4)
                for ((index, option) in question.options.withIndex()) {
                    optionButtons[index].text = option
                    optionButtons[index].visibility = View.VISIBLE
                    optionButtons[index].setOnClickListener {
                        checkAnswer(option)
                    }
                }
                for (i in question.options.size until optionButtons.size) {
                    optionButtons[i].visibility = View.GONE // Eğer şık sayısı 4'ten az ise gizle
                }
            } else {
                Snackbar.make(binding.root, "Quiz Finished!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkAnswer(selectedOption: String) {
        val correctAnswer = viewModel.currentQuestion.value?.correctAnswer
        if (selectedOption == correctAnswer) {
            Snackbar.make(binding.root, "Correct!", Snackbar.LENGTH_SHORT).show()
        } else {
            Snackbar.make(binding.root, "Wrong! Correct answer: $correctAnswer", Snackbar.LENGTH_SHORT).show()
        }
    }
}
