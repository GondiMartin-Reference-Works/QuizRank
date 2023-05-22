package com.example.quizrank.data.topics.firebase

import com.example.quizrank.domain.model.Topic
import com.google.firebase.firestore.DocumentId

data class FirebaseTopic(
    @DocumentId val id: String = "",
    val title: String = "",
)

fun FirebaseTopic.asTopic() = Topic(
    id = id,
    title = title,
)

fun Topic.asFirebaseTopic() = FirebaseTopic(
    id = id,
    title = title,
)