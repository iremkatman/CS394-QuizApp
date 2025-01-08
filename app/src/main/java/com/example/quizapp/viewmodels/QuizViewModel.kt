package com.example.quizapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizapp.models.Question
import com.example.quizapp.network.ApiResponse
import com.example.quizapp.network.RetrofitClient
import com.example.quizapp.network.QuizApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
    private val firestore = FirebaseFirestore.getInstance()
    private val firebaseAuth = FirebaseAuth.getInstance()

    // Quiz sorularını çekmek için
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
                println("Failed to fetch questions: ${t.message}")
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

    fun markAnswered(isCorrect: Boolean, difficulty: String) {
        if (isCorrect) {
            correctAnswersCount += when (difficulty) {
                "easy" -> 10
                "medium" -> 20
                "hard" -> 30
                else -> 0
            }
        }
        _isAnswered.value = true
    }

    fun getCorrectAnswersCount(): Int = correctAnswersCount
    fun getTotalQuestionsCount(): Int = _questions.value?.size ?: 0









    fun saveScoreToFirestore() {
        val userId = firebaseAuth.currentUser?.uid
        val nickname = firebaseAuth.currentUser?.email?.substringBefore("@") ?: "Guest"
        val currentGameScore = correctAnswersCount // Bu oyun için doğru cevap sayısı

        if (userId != null) {
            val userDocRef = firestore.collection("Leaderboard").document(userId)

            // Firestore'dan mevcut toplam skoru al
            userDocRef.get().addOnSuccessListener { document ->
                if (document.exists()) {
                    // Mevcut toplam skor varsa, ona ekleme yap
                    val previousTotalScore = document.getLong("totalScore") ?: 0L
                    val updatedScore = previousTotalScore + currentGameScore

                    // Güncellenmiş skoru kaydet
                    val userScore = hashMapOf(
                        "nickname" to nickname,
                        "totalScore" to updatedScore,
                        "userId" to userId
                    )

                    userDocRef.set(userScore)
                        .addOnSuccessListener {
                            println("Score updated successfully!")
                        }
                        .addOnFailureListener { exception ->
                            println("Error updating score: ${exception.message}")
                        }
                } else {
                    // Kullanıcı yoksa yeni bir belge oluştur
                    val userScore = hashMapOf(
                        "nickname" to nickname,
                        "totalScore" to currentGameScore,
                        "userId" to userId
                    )

                    userDocRef.set(userScore)
                        .addOnSuccessListener {
                            println("New user score saved successfully!")
                        }
                        .addOnFailureListener { exception ->
                            println("Error saving new user score: ${exception.message}")
                        }
                }
            }.addOnFailureListener { exception ->
                println("Error retrieving user document: ${exception.message}")
            }
        } else {
            println("Error: User ID is null")
        }
    }
}
