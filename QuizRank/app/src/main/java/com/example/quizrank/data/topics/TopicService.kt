package com.example.quizrank.data.topics

import com.example.quizrank.domain.model.Topic
import kotlinx.coroutines.flow.Flow

interface TopicService {
    val topics: Flow<List<Topic>>

    suspend fun getTopic(id: String): Topic?

    suspend fun saveTopic(topic: Topic)

    suspend fun updateTopic(topic: Topic)

    suspend fun deleteTopic(id: String)
}