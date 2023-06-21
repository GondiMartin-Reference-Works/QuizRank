package com.example.quizrank.data.questions.firebase

import com.example.quizrank.domain.model.Question
import com.google.firebase.firestore.DocumentId

data class FirebaseQuestion(
    @DocumentId val id: String = "",
    val text: String = "",
    val value: String = "",
    val answers: Map<String, String> = mapOf("A" to "B")
)

fun FirebaseQuestion.asQuestion() = Question(
    id = id,
    text = text,
    value = value,
    answers = answers
)

fun Question.asFirebaseQuestion() = FirebaseQuestion(
    id = id,
    text = text,
    value = value,
    answers = answers
)