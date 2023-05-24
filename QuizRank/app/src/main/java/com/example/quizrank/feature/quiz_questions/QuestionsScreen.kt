package com.example.quizrank.feature.quiz_questions

import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.ManageSearch
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quizrank.R
import com.example.quizrank.feature.quiz_topics.TopicsViewModel
import com.example.quizrank.ui.common.ButtonWithIcon
import com.example.quizrank.ui.common.QuizRankAppBar
import com.example.quizrank.ui.model.toUiText
import java.util.Stack

@Preview
@Composable
fun QuestionsScreenPreview(){
    QuestionsScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionsScreen(
    //viewModel: QuestionsViewModel = viewModel(factory = QuestionsViewModel.Factory)
){
    // TODO View of Questions with QuestionScreen or UI (not sure)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            QuizRankAppBar(
                title = stringResource(id = R.string.questions_page),
                actions = {
                    IconButton(onClick = { /*TODO: LeaderBoard*/ }) {
                        Icon(imageVector = Icons.Default.Leaderboard, contentDescription = null)
                    }
                    IconButton(onClick = {}
                    ) {
                        Icon(imageVector = Icons.Default.Logout, contentDescription = null)
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
                    color = MaterialTheme.colorScheme.secondaryContainer
                ),
            contentAlignment = Alignment.Center
        )
        {

            Column(
                modifier = Modifier
                    .padding(start = 20.dp, top = 60.dp, end = 20.dp, bottom = 30.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ){
                Row(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .fillMaxWidth()
                        .fillMaxHeight(0.35f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = "Put Question here LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO!",
                        modifier = Modifier.padding(10.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontFamily = FontFamily.Serif,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.fillMaxWidth(),
                            shape = CutCornerShape(10.dp)
                        ) {
                            Text(text = "Abraham Lincoln George")
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.fillMaxWidth(),
                            shape = CutCornerShape(10.dp)
                        ) {
                            Text(text = "C")
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.fillMaxWidth(),
                            shape = CutCornerShape(10.dp)
                        ) {
                            Text(text = "Abraham Lincoln George")
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.fillMaxWidth(),
                            shape = CutCornerShape(10.dp)
                        ) {
                            Text(text = "Abraham Lincoln George")
                        }
                    }
                }
            }
        }
    }
}

