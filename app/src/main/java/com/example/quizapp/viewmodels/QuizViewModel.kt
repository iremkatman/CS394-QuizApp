package com.example.quizapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizapp.models.Question

class QuizViewModel : ViewModel() {

    private val _currentQuestion = MutableLiveData<Question>()
    val currentQuestion: LiveData<Question> get() = _currentQuestion

    private var questionIndex = 0

    private val questions = listOf(
        Question(
            text = "What is the capital of France?",
            options = listOf("Paris", "London", "Berlin", "Madrid"),
            correctAnswer = "Paris"
        ),
        Question(
            text = "What is 2 + 2?",
            options = listOf("3", "4", "5", "6"),
            correctAnswer = "4"
        ),
        Question(
            text = "Who wrote 'Hamlet'?",
            options = listOf("Charles Dickens", "William Shakespeare", "Mark Twain", "J.K. Rowling"),
            correctAnswer = "William Shakespeare"
        )
    )

    init {
        loadQuestion()
    }

    fun loadQuestion() {
        if (questionIndex < questions.size) {
            _currentQuestion.value = questions[questionIndex]
        } else {
            _currentQuestion.value = null // Tüm sorular bitti
        }
    }

    fun nextQuestion() {
        questionIndex++
        loadQuestion()
    }
}
