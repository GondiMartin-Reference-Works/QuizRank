package com.example.quizrank.ui.model

import com.example.quizrank.domain.model.Question

data class QuestionUi(
    val id: String = "",
    val text: String = "",
    val answers: Map<String, String>,
    val value: String = "",
)

fun Question.asQuestionUi(): QuestionUi = QuestionUi(
    id = id,
    text = text,
    answers = answers,
    value = value,
)

fun QuestionUi.asQuestion(): Question = Question(
    id = id,
    text = text,
    answers = answers,
    value = value,
)