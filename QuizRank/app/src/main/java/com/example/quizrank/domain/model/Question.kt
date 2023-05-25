package com.example.quizrank.domain.model

/*data class Question(
    val id: String = "",
    val text: String = "",
    val value: String = "",
    val option1: String = "",
    val option2: String = "",
    val option3: String = "",
    val option4: String = "",
    val optionCount: Int = 4
)*/

data class Question(
    val id: String = "",
    val text: String = "",
    val answers: Map<String, String>,
    val value: String = "",
)
