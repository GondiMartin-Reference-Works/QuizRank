package com.example.quizrank.feature.quiz_topic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.quizrank.QuizRankApplication
import com.example.quizrank.data.auth.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TopicsViewModel constructor(
    private val authService: AuthService
) : ViewModel() {

    private val _state = MutableStateFlow(TopicsState())
    val state = _state.asStateFlow()


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val authService = QuizRankApplication.authService
                TopicsViewModel(
                    authService = authService
                )
            }
        }
    }
}

data class TopicsState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isError: Boolean = error != null
)