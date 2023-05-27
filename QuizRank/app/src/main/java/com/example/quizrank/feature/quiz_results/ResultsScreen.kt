package com.example.quizrank.feature.quiz_results

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quizrank.R
import com.example.quizrank.ui.common.QuizRankAppBar
import com.example.quizrank.ui.model.toUiText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultsScreen(
    onQuit: () -> Unit = { },
    viewModel: ResultsViewModel = viewModel(factory = ResultsViewModel.Factory)
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            QuizRankAppBar(
                title = stringResource(id = R.string.results_page),
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
        )
        {
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
                if(state.results.isEmpty()){
                    Text(text = stringResource(id = R.string.text_empty_results_list))
                }else{
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(5.dp))
                    ) {
                        items(state.results.size) { i ->
                            ListItem(
                                headlineText = {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Icon(
                                            imageVector = viewModel.setIcon(state.results[i].result),
                                            contentDescription = null,
                                            tint = Color.DarkGray,
                                            modifier = Modifier
                                                .size(50.dp)
                                                .padding(
                                                    end = 8.dp,
                                                    top = 8.dp,
                                                    bottom = 8.dp
                                                )
                                                .fillMaxWidth(0.2f),
                                        )
                                        Column(
                                            modifier = Modifier
                                                .padding(4.dp, 0.dp)
                                                .fillMaxWidth(0.35f),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ){
                                            Text(
                                                text = state.results[i].name,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 20.sp,
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                        Column(
                                            modifier = Modifier
                                                .padding(4.dp, 0.dp)
                                                .fillMaxWidth(0.45f),
                                            horizontalAlignment = Alignment.End
                                        ){
                                            Text(
                                                text = state.results[i].topic,
                                                fontWeight = FontWeight.Bold,
                                                textAlign = TextAlign.End
                                            )
                                            Text(
                                                text = state.results[i].result.toString(),
                                                fontStyle = FontStyle.Italic,
                                                color = Color.Gray,
                                                textAlign = TextAlign.End
                                            )
                                        }
                                    }
                                },
                            )
                            if (i != state.results.lastIndex) {
                                Divider(
                                    thickness = 2.dp,
                                    color = MaterialTheme.colorScheme.secondaryContainer
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ResultsScreenPreview(){
    ResultsScreen()
}