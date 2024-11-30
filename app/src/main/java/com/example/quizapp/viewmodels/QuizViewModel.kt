package com.example.quizapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizapp.models.Question

class QuizViewModel : ViewModel() {

    private val _currentQuestion = MutableLiveData<Question?>()
    val currentQuestion: LiveData<Question?> get() = _currentQuestion

    private val _isAnswered = MutableLiveData<Boolean>()
    val isAnswered: LiveData<Boolean> get() = _isAnswered

    private var questionIndex = 0
    private var correctAnswers = 0

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
            _isAnswered.value = false
        } else {
            _currentQuestion.value = null
        }
    }

    fun nextQuestion() {
        questionIndex++
        loadQuestion()
    }

    fun checkAnswer(selectedOption: String): Boolean {
        if (_isAnswered.value == true) return false
        val isCorrect = _currentQuestion.value?.correctAnswer == selectedOption
        if (isCorrect) {
            correctAnswers++
        }
        _isAnswered.value = true
        return isCorrect
    }

    fun getCorrectAnswersCount(): Int {
        return correctAnswers
    }

    fun getTotalQuestionsCount(): Int {
        return questions.size
    }
}
