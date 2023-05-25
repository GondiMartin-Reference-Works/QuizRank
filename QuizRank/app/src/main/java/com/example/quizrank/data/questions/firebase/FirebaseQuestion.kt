package com.example.quizrank.data.questions.firebase

import com.example.quizrank.domain.model.Question
import com.google.firebase.firestore.DocumentId

data class FirebaseQuestion(
    @DocumentId val id: String = "",
    val text: String = "",
    val value: String = "",
    val option1: String = "",
    val option2: String = "",
    val option3: String = "",
    val option4: String = "",
    val optionCount: Int = 4
)

fun FirebaseQuestion.asQuestion() = Question(
    id = id,
    text = text,
    value = value,
    option1 = option1,
    option2 = option2,
    option3 = option3,
    option4 = option4,
    optionCount = optionCount
)

fun Question.asFirebaseQuestion() = FirebaseQuestion(
    id = id,
    text = text,
    value = value,
    option1 = option1,
    option2 = option2,
    option3 = option3,
    option4 = option4,
    optionCount = optionCount
)