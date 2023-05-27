package com.example.quizrank.feature.quiz_questions

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.quizrank.QuizRankApplication
import com.example.quizrank.data.questions.QuestionService
import com.example.quizrank.ui.model.QuestionUi
import com.example.quizrank.ui.model.asQuestionUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuestionsViewModel constructor(
    private val savedState: SavedStateHandle,
    private val questionService: QuestionService
) : ViewModel() {

    private val _state = MutableStateFlow(QuestionsState())
    val state = _state.asStateFlow()
    init {
        loadQuestions()
    }

    private fun loadQuestions() {
        val topicId = checkNotNull<String>(savedState["id"])
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                questionService.setTopicId(topicId)
                questionService.questions.collect{
                    val questions = it.map {it.asQuestionUi()}
                    _state.update { it.copy(isLoading = false, questions = questions) }
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e) }
            }
        }
    }

    fun onButtonClick(clickedButtonText: String) {
        _state.update { it.copy(
            currentQuestionIndex = it.currentQuestionIndex + 1,
            goodAnswerCount = it.goodAnswerCount + resultOfGivenAnswer(it.questions[it.currentQuestionIndex].value, clickedButtonText)
        ) }
    }

    private fun resultOfGivenAnswer(expectedText: String, actualText: String): Int{
        Log.d("expectedText-actualText", "$expectedText-$actualText")
        if(expectedText.equals(actualText))
            return 1
        return 0
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val questionService = QuizRankApplication.questionService
                val savedState = createSavedStateHandle()
                QuestionsViewModel(
                    questionService = questionService,
                    savedState = savedState
                )
            }
        }
    }
}
data class QuestionsState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isError: Boolean = error != null,
    val questions: List<QuestionUi> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val goodAnswerCount: Int = 0
)