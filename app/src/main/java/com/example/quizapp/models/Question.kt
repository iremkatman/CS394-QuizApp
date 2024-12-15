package com.example.quizapp.models

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("question") val text: String,
    @SerializedName("correct_answer") val correctAnswer: String,
    @SerializedName("incorrect_answers") val incorrectAnswers: List<String>
) {
    val options: List<String>
        get() = (incorrectAnswers + correctAnswer).shuffled()
}
