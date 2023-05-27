package com.example.quizrank.feature.quiz_results

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material.icons.filled.SentimentNeutral
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material.icons.filled.SentimentVerySatisfied
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.quizrank.QuizRankApplication
import com.example.quizrank.data.results.ResultService
import com.example.quizrank.ui.model.QuizRankUserUi
import com.example.quizrank.ui.model.ResultUi
import com.example.quizrank.ui.model.asResultUi
import com.example.quizrank.ui.model.toUiText
import com.example.quizrank.util.UiEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ResultsViewModel constructor(
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
                        it1.copy(isLoading = false, results = results.sortedWith(
                        compareBy ({ -it.result }, { it.name }))) }
                }
            }catch (e: Exception){
                _state.update { it.copy(isLoading = false, error = e) }
            }
        }
    }

    fun setIcon(value: Int): ImageVector {
        if (value in 1..2)
            return Icons.Default.SentimentNeutral
        else if (value in 3 .. 4)
            return Icons.Default.SentimentSatisfied
        else if (value >= 5)
            return Icons.Default.SentimentVerySatisfied
        return Icons.Default.SentimentVeryDissatisfied
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val resultService = QuizRankApplication.resultService
                ResultsViewModel(
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
    val results: List<ResultUi> = emptyList()
)