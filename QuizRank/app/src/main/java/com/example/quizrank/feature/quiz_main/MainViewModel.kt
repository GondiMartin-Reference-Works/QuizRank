package com.example.quizrank.feature.quiz_main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.quizrank.QuizRankApplication
import com.example.quizrank.data.auth.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel constructor(
    private val authService: AuthService
) : ViewModel() {

    private val _state = MutableStateFlow(QuizState())
    val state = _state.asStateFlow()

    fun signOut() {
        viewModelScope.launch {
            authService.signOut()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val authService = QuizRankApplication.authService
                MainViewModel(
                    authService = authService
                )
            }
        }
    }
}

data class QuizState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isError: Boolean = error != null
)