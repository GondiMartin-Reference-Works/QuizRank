package com.example.quizrank.data.results.firebase

import com.example.quizrank.domain.model.Result
import com.google.firebase.firestore.DocumentId

data class FirebaseResult(
    @DocumentId val id: String = "",
    val topic: String = "",
    val result: Int = 0,
    val name: String = ""
)

fun FirebaseResult.asResult() = Result(
    id = id,
    topic = topic,
    result = result,
    name = name
)

fun Result.asFirebaseResult() = FirebaseResult(
    id = id,
    topic = topic,
    result = result,
    name = name
)