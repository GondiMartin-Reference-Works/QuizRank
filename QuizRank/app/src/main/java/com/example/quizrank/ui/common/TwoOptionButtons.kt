package com.example.quizrank.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TwoOptionButtons(
    onButtonClick: (text: String) -> Unit = { },
    firstButtonText: String = "Fact",
    secondButtonText: String = "Fiction"
){
    Column(
        modifier = Modifier
            .padding(start = 20.dp, top = 5.dp, bottom = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(2.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Button(
                onClick = { onButtonClick(firstButtonText) },
                modifier = Modifier.padding(2.dp, 0.dp).fillMaxWidth().weight(0.5f),
                shape = CutCornerShape(10.dp)
            ) {
                Text(text = firstButtonText)
            }
            Button(
                onClick = { onButtonClick(secondButtonText) },
                modifier = Modifier.padding(2.dp, 0.dp).fillMaxWidth().weight(0.5f),
                shape = CutCornerShape(10.dp)
            ) {
                Text(text = secondButtonText)
            }
        }
    }
}

@Preview
@Composable
fun TwoOptionButtonsPreview(){
    TwoOptionButtons()
}