package com.example.quizrank.feature.quiz_questions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quizrank.ui.common.QuestionScreen

@Composable
fun QuestionsScreen(
    onQuit: () -> Unit = {},
    viewModel: QuestionsViewModel = viewModel(factory = QuestionsViewModel.Factory)
){

    val state by viewModel.state.collectAsStateWithLifecycle()
    // TODO Try not to give whole state

    QuestionScreen(
        state = state,
        onQuit = onQuit,
        onButtonClick = {},
        currentQuestionIndex = viewModel.currentQuestionIndex
    )
}

@Preview
@Composable
fun QuestionsScreenPreview(){
    QuestionsScreen()
}

