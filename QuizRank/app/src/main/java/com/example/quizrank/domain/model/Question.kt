package com.example.quizrank.domain.model

data class Question(
    val id: String = "",
    val text: String = "",
    val answers: Map<String, String>,
    val value: String = "",
)
