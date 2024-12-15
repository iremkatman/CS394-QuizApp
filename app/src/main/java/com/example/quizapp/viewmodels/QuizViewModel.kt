package com.example.quizapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizapp.models.Question
import com.example.quizapp.network.ApiResponse
import com.example.quizapp.network.RetrofitClient
import com.example.quizapp.network.QuizApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class QuizViewModel : ViewModel() {

    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> get() = _questions

    private val _currentQuestionIndex = MutableLiveData(0)
    val currentQuestionIndex: LiveData<Int> get() = _currentQuestionIndex

    private val _isAnswered = MutableLiveData(false)
    val isAnswered: LiveData<Boolean> get() = _isAnswered

    private val _isQuizFinished = MutableLiveData(false)
    val isQuizFinished: LiveData<Boolean> get() = _isQuizFinished

    private var correctAnswersCount = 0

    fun fetchQuestions(amount: Int, categoryId: Int, difficulty: String) {
        val api = RetrofitClient.instance.create(QuizApi::class.java)
        api.getQuestions(amount, categoryId, difficulty).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                _questions.value = response.body()?.results ?: emptyList()
                _currentQuestionIndex.value = 0
                correctAnswersCount = 0
                _isQuizFinished.value = false
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            }
        })
    }

    fun getCurrentQuestion(): Question? {
        return _questions.value?.getOrNull(_currentQuestionIndex.value ?: 0)
    }

    fun nextQuestion() {
        if (_currentQuestionIndex.value != null) {
            if (_currentQuestionIndex.value!! < (_questions.value?.size ?: 0) - 1) {
                _currentQuestionIndex.value = _currentQuestionIndex.value!! + 1
                _isAnswered.value = false
            } else {
                _isQuizFinished.value = true
            }
        }
    }

    fun markAnswered(isCorrect: Boolean) {
        if (isCorrect) correctAnswersCount++
        _isAnswered.value = true
    }

    fun getCorrectAnswersCount(): Int = correctAnswersCount
    fun getTotalQuestionsCount(): Int = _questions.value?.size ?: 0
    fun reset() {
        _questions.value = emptyList()
        _currentQuestionIndex.value = 0
        _isQuizFinished.value = false
        correctAnswersCount = 0
    }
}
