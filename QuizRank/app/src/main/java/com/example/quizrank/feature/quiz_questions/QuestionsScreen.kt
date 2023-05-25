package com.example.quizrank.feature.quiz_questions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quizrank.R
import com.example.quizrank.ui.common.FourOptionQuestion
import com.example.quizrank.ui.common.QuizRankAppBar
import com.example.quizrank.ui.common.TwoOptionQuestion
import com.example.quizrank.ui.model.QuestionUi
import com.example.quizrank.ui.model.toUiText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionsScreen(
    onQuit: () -> Unit = {},
    viewModel: QuestionsViewModel = viewModel(factory = QuestionsViewModel.Factory)
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            QuizRankAppBar(
                title = stringResource(id = R.string.questions_page),
                actions = {
                    IconButton(onClick = {
                        onQuit()
                    }) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    color = if (!state.isLoading && !state.isError) {
                        MaterialTheme.colorScheme.secondaryContainer
                    } else {
                        MaterialTheme.colorScheme.background
                    }
                ),
            contentAlignment = Alignment.Center
        ){
            if (state.isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.secondaryContainer
                )
            } else if (state.isError) {
                Text(
                    text = state.error?.toUiText()?.asString(context)
                        ?: stringResource(id = R.string.some_error_message)
                )
            } else{
                Text("Here we are")
                val question: QuestionUi = state.questions[viewModel.currentQuestionIndex]
                if (question.optionCount == 2)
                    TwoOptionQuestion(
                        onButtonClick = viewModel::onButtonClick,
                        text = question.text,
                        firstButtonText = question.option1,
                        secondButtonText = question.option2
                        //firstButtonText = question.answers.values.elementAt(0),
                        //secondButtonText = question.answers.values.elementAt(1)
                    )
                else
                    FourOptionQuestion(
                        onButtonClick = viewModel::onButtonClick,
                        text = question.text,
                        firstButtonText = question.option1,
                        secondButtonText = question.option2,
                        thirdButtonText = question.option3,
                        fourthButtonText = question.option4
                        /*firstButtonText = question.answers.values.elementAt(0),
                        secondButtonText = question.answers.values.elementAt(1),
                        thirdButtonText = question.answers.values.elementAt(2),
                        fourthButtonText = question.answers.values.elementAt(3)*/
                    )
            }
        }
    }
}

@Preview
@Composable
fun QuestionsScreenPreview(){
    QuestionsScreen()
}

