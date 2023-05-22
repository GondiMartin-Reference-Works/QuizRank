package com.example.quizrank.feature.quiz_topic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.quizrank.QuizRankApplication
import com.example.quizrank.data.auth.AuthService
import com.example.quizrank.data.topics.TopicService
import com.example.quizrank.ui.model.QuestionUi
import com.example.quizrank.ui.model.TopicUi
import com.example.quizrank.ui.model.asTopicUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TopicsViewModel constructor(
    private val authService: AuthService,
    private val topicService: TopicService
) : ViewModel() {

    private val _state = MutableStateFlow(TopicsState())
    val state = _state.asStateFlow()

    init {
        loadTopics()
    }

    private fun loadTopics(){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true)  }
            try{
                topicService.topics.collect{
                    val topics = it.map { it.asTopicUi() }
                    _state.update { it.copy(isLoading = false, topics = topics) }
                }
            }catch (e: Exception){
                _state.update { it.copy(isLoading = false, error = e) }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val authService = QuizRankApplication.authService
                val topicService = QuizRankApplication.topicService
                TopicsViewModel(
                    authService = authService,
                    topicService = topicService
                )
            }
        }
    }
}

data class TopicsState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isError: Boolean = error != null,
    val topics: List<TopicUi> = emptyList()
)