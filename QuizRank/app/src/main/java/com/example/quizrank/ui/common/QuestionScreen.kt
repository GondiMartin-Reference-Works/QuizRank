package com.example.quizrank.ui.common

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizrank.R
import com.example.quizrank.feature.quiz_questions.QuestionsState
import com.example.quizrank.ui.model.QuestionUi
import com.example.quizrank.ui.model.toUiText
import kotlin.collections.Map as Map

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionScreen(
    state: QuestionsState,
    onQuit: () -> Unit = { },
    onButtonClick: () -> Unit = { },
    currentQuestionIndex: Int = 0
){
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
                val question: QuestionUi = state.questions[currentQuestionIndex]
                if (state.questions.size == 2)
                    TwoOptionQuestion(
                        onButtonClick = onButtonClick,
                        text = question.text,
                        firstButtonText = question.answers.values.elementAt(0),
                        secondButtonText = question.answers.values.elementAt(1)
                    )
                else
                    FourOptionQuestion(
                        onButtonClick = onButtonClick,
                        text = question.text,
                        firstButtonText = question.answers.values.elementAt(0),
                        secondButtonText = question.answers.values.elementAt(1),
                        thirdButtonText = question.answers.values.elementAt(2),
                        fourthButtonText = question.answers.values.elementAt(3)
                    )
            }
        }
    }
}