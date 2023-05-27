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
import com.example.quizrank.data.results.ResultService
import com.example.quizrank.domain.model.Result
import com.example.quizrank.ui.model.QuestionUi
import com.example.quizrank.ui.model.asQuestionUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuestionsViewModel constructor(
    private val savedState: SavedStateHandle,
    private val questionService: QuestionService,
    private val resultService: ResultService
) : ViewModel() {

    private val _state = MutableStateFlow(QuestionsState())
    val state = _state.asStateFlow()

    private var resultId: Int = 0
    init {
        loadQuestions()
    }

    private fun loadQuestions() {
        val topicId = "topic-"+checkNotNull<String>(savedState["title"]).split("-")[0]
        Log.d("topicID", topicId)
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
        if(expectedText.equals(actualText))
            return 1
        return 0
    }

    fun onSave(name: String){
        val topicTitle = checkNotNull<String>(savedState["title"]).split("-")[1]
        viewModelScope.launch {
            try {
                CoroutineScope(coroutineContext).launch(Dispatchers.IO) {
                    resultService.saveResult(Result(
                        id = (resultId++).toString(),
                        topic = topicTitle,
                        result = _state.value.goodAnswerCount,
                        name = name,
                    ))
                }
            } catch (e: Exception) {
                throw Exception(e)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val questionService = QuizRankApplication.questionService
                val savedState = createSavedStateHandle()
                val resultService = QuizRankApplication.resultService
                QuestionsViewModel(
                    questionService = questionService,
                    savedState = savedState,
                    resultService = resultService
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