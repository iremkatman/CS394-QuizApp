package com.example.quizapp.network

import com.example.quizapp.models.Question
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApi {
    @GET("api.php")
    fun getQuestions(
        @Query("amount") amount: Int,
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String
    ): Call<ApiResponse>
}

data class ApiResponse(val results: List<Question>)
