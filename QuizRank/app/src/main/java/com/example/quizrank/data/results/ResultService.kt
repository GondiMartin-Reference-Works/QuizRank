package com.example.quizrank.data.results

import kotlinx.coroutines.flow.Flow
import com.example.quizrank.domain.model.Result

interface ResultService {
    val results: Flow<List<Result>>
    suspend fun saveResult(result: Result)

    suspend fun deleteResults(ids: List<String>)
}