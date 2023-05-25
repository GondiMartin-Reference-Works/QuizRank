package com.example.quizrank.ui.model

import com.example.quizrank.domain.model.Question


/*data class QuestionUi(
    val id: String = "",
    val text: String = "",
    val value: String = "",
    val option1: String = "",
    val option2: String = "",
    val option3: String = "",
    val option4: String = "",
    val optionCount: Int = 4
)

fun Question.asQuestionUi(): QuestionUi = QuestionUi(
    id = id,
    text = text,
    value = value,
    option1 = option1,
    option2 = option2,
    option3 = option3,
    option4 = option4,
    optionCount = optionCount
)

fun QuestionUi.asQuestion(): Question = Question(
    id = id,
    text = text,
    value = value,
    option1 = option1,
    option2 = option2,
    option3 = option3,
    option4 = option4,
    optionCount = optionCount
)*/


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