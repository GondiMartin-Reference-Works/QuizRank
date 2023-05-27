package com.example.quizrank.ui.model

import com.example.quizrank.domain.model.QuizRankUser


data class QuizRankUserUi(
    val id: String = "",
    val name: String = ""
)

fun QuizRankUser.asQuizRankUserUi(): QuizRankUserUi = QuizRankUserUi(
    id = id,
    name = name
)

fun QuizRankUserUi.QuizRankUser(): QuizRankUser = QuizRankUser(
    id = id,
    name = name
)