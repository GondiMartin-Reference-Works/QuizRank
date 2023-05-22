package com.example.quizrank.data.questions.firebase

import com.example.quizrank.domain.model.Question
import com.google.firebase.firestore.DocumentId

data class FirebaseQuestion(
    @DocumentId val id: String = "",
    val title: String = "",
    val text: String = "",
    val answers: Map<String, String>,
    val value: String = "",
)

fun FirebaseQuestion.asQuestion() = Question(
    id = id,
    text = text,
    answers = answers,
    value = value,
)

fun Question.asFirebaseQuestion() = FirebaseQuestion(
    id = id,
    text = text,
    answers = answers,
    value = value,
)