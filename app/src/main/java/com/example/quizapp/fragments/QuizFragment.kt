package com.example.quizapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentQuizBinding
import com.example.quizapp.viewmodels.QuizViewModel

class QuizFragment : Fragment() {
    private lateinit var binding: FragmentQuizBinding
    private val viewModel: QuizViewModel by viewModels()
    private var selectedOption: Button? = null
    private lateinit var difficulty: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(inflater, container, false)

        val args = QuizFragmentArgs.fromBundle(requireArguments())
        val categoryId = args.categoryId
        difficulty = args.difficulty

        viewModel.fetchQuestions(5, categoryId, difficulty)
        setupObservers()

        listOf(binding.btnOption1, binding.btnOption2, binding.btnOption3, binding.btnOption4).forEach { button ->
            button.setOnClickListener { onOptionSelected(button) }
        }

        binding.btnNext.setOnClickListener {
            if (viewModel.isQuizFinished.value == true) {
                viewModel.saveScoreToFirestore() // Puanı kaydet
                navigateToResults()
            } else {
                viewModel.nextQuestion()
                resetOptionColors()
                enableOptions(true)
                setupQuestionView()
            }
        }

        return binding.root
    }

    private fun setupQuestionView() {
        val currentQuestion = viewModel.getCurrentQuestion()
        binding.tvQuestion.text = currentQuestion?.text
        val options = currentQuestion?.options ?: emptyList()

        binding.btnOption1.text = options.getOrNull(0)
        binding.btnOption2.text = options.getOrNull(1)
        binding.btnOption3.text = options.getOrNull(2)
        binding.btnOption4.text = options.getOrNull(3)
    }

    private fun onOptionSelected(option: Button) {
        val correctAnswer = viewModel.getCurrentQuestion()?.correctAnswer
        val isCorrect = option.text == correctAnswer

        if (isCorrect) {
            option.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green))
        } else {
            option.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
            highlightCorrectAnswer(correctAnswer)
        }

        viewModel.markAnswered(isCorrect, difficulty)
        enableOptions(false)
    }

    private fun highlightCorrectAnswer(correctAnswer: String?) {
        listOf(binding.btnOption1, binding.btnOption2, binding.btnOption3, binding.btnOption4).forEach { button ->
            if (button.text == correctAnswer) {
                button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green))
            }
        }
    }

    private fun resetOptionColors() {
        listOf(binding.btnOption1, binding.btnOption2, binding.btnOption3, binding.btnOption4).forEach { button ->
            button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.default_option))
        }
    }

    private fun enableOptions(enable: Boolean) {
        listOf(binding.btnOption1, binding.btnOption2, binding.btnOption3, binding.btnOption4).forEach { button ->
            button.isEnabled = enable
        }
    }

    private fun navigateToResults() {
        val action = QuizFragmentDirections.actionQuizFragmentToResultsFragment(
            viewModel.getCorrectAnswersCount(),
            viewModel.getTotalQuestionsCount()
        )
        findNavController().navigate(action)
    }

    private fun setupObservers() {
        viewModel.questions.observe(viewLifecycleOwner) { questions ->
            if (questions.isNotEmpty()) {
                setupQuestionView()
            }
        }

        viewModel.isQuizFinished.observe(viewLifecycleOwner) { isFinished ->
            if (isFinished) {
                viewModel.saveScoreToFirestore() // Puan kaydı
                navigateToResults()
            }
        }
    }
}
