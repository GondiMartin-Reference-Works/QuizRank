package com.example.quizrank.ui.model

import com.example.quizrank.domain.model.Topic

data class TopicUi(
    val id: String = "",
    val title: String = "",
    val description: String = ""
)

fun Topic.asTopicUi(): TopicUi = TopicUi(
    id = id,
    title = title,
    description = description
)

fun TopicUi.asTopic(): Topic = Topic(
    id = id,
    title = title,
    description = description
)