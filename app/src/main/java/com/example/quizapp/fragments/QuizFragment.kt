package com.example.quizapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentQuizBinding
import com.example.quizapp.models.Question
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
                setupQuestionView(question)
            } else {
                navigateToResults()
            }
        }

        viewModel.isAnswered.observe(viewLifecycleOwner) { isAnswered ->
            binding.btnNext.isEnabled = isAnswered
        }
    }

    private fun setupQuestionView(question: Question) {
        binding.tvQuestion.text = question.text

        val optionButtons = listOf(binding.btnOption1, binding.btnOption2, binding.btnOption3, binding.btnOption4)
        for ((index, option) in question.options.withIndex()) {
            val button = optionButtons[index]
            button.text = option
            button.visibility = View.VISIBLE
            button.isEnabled = true
            button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.deepPurple))

            button.setOnClickListener {
                if (viewModel.isAnswered.value == true) return@setOnClickListener

                val isCorrect = viewModel.checkAnswer(option)
                if (isCorrect) {
                    button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green)) // Correct: Green
                } else {
                    button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red)) // Incorrect: Red
                    highlightCorrectAnswer(optionButtons, question.correctAnswer)
                }
                Snackbar.make(binding.root, if (isCorrect) "Correct!" else "Wrong!", Snackbar.LENGTH_SHORT).show()

                optionButtons.forEach { it.isEnabled = false }
            }
        }
        for (i in question.options.size until optionButtons.size) {
            optionButtons[i].visibility = View.GONE // Hide unused buttons
        }
    }

    private fun highlightCorrectAnswer(buttons: List<View>, correctAnswer: String) {
        buttons.forEach { button ->
            if ((button as? android.widget.Button)?.text == correctAnswer) {
                button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green)) // Highlight correct button
            }
        }
    }

    private fun navigateToResults() {
        val action = QuizFragmentDirections.actionQuizFragmentToResultsFragment(
            viewModel.getCorrectAnswersCount(),
            viewModel.getTotalQuestionsCount()
        )
        findNavController().navigate(action)
    }
}
