package com.example.quizrank.data.questions

import com.example.quizrank.domain.model.Question
import kotlinx.coroutines.flow.Flow

interface QuestionService {
    val questions: Flow<List<Question>>
}