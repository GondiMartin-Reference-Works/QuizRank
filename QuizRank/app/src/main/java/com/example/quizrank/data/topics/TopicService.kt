package com.example.quizrank.data.topics

import com.example.quizrank.domain.model.Topic
import kotlinx.coroutines.flow.Flow

interface TopicService {
    val topics: Flow<List<Topic>>
}