package com.example.quizrank.feature.quiz_results

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.quizrank.QuizRankApplication
import com.example.quizrank.data.auth.AuthService
import com.example.quizrank.data.results.ResultService
import com.example.quizrank.ui.model.QuizRankUserUi
import com.example.quizrank.ui.model.ResultUi
import com.example.quizrank.ui.model.asResultUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.quizrank.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource

class ResultsViewModel constructor(
    private val authService: AuthService,
    private val resultService: ResultService
) : ViewModel() {

    private val _state = MutableStateFlow(ResultsState())
    val state = _state.asStateFlow()

    init {
        loadResults()
    }

    private fun loadResults(){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true)  }
            try{
                resultService.results.collect{ it ->
                    val results = it.map { it.asResultUi() }
                    _state.update { it1 ->
                        it1.copy(isLoading = false,
                            results = results.sortedWith(compareBy ({ -it.result }, { it.topic })),
                            currentUserId = authService.currentUserId ?: "null"
                        )}
                }
            }catch (e: Exception){
                _state.update { it.copy(isLoading = false, error = e) }
            }
        }
    }

    fun setColor(userId: String): Color {
        if(userId == state.value.currentUserId)
            return Color.DarkGray
        return Color.LightGray
    }

    fun deleteResults() {
        val ids = state.value.results.filter { result ->
            result.userId == state.value.currentUserId }.map { result ->
            result.id
        }
        viewModelScope.launch {
            try {
                CoroutineScope(coroutineContext).launch(Dispatchers.IO) {
                    resultService.deleteResults(ids)
                }
            } catch (e: Exception) {
                throw Exception(e)
            }
        }
    }

    @Composable
    fun setIcon(value: Int): ImageVector {
        return when (value) {
            0 -> ImageVector.vectorResource(id = R.drawable.sentiment_very_dissatisfied)
            1 -> ImageVector.vectorResource(R.drawable.sentiment_frustrated)
            2 -> ImageVector.vectorResource(R.drawable.sentiment_sad)
            3 -> ImageVector.vectorResource(R.drawable.sentiment_content)
            4 -> ImageVector.vectorResource(R.drawable.sentiment_calm)
            5 -> ImageVector.vectorResource(R.drawable.sentiment_excited)
            else -> Icons.Default.SentimentSatisfied
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val resultService = QuizRankApplication.resultService
                val authService = QuizRankApplication.authService
                ResultsViewModel(
                    authService = authService,
                    resultService = resultService
                )
            }
        }
    }
}

data class ResultsState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isError: Boolean = error != null,
    val users: List<QuizRankUserUi> = emptyList(),
    val results: List<ResultUi> = emptyList(),
    val currentUserId: String = ""
)
