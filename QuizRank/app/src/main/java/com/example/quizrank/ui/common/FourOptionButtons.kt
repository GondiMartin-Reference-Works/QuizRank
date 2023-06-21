package com.example.quizrank.ui.common

import androidx.compose.foundation.layout.Column
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
fun FourOptionButtons(
    onButtonClick: (text: String) -> Unit = { },
    firstButtonText: String = "A",
    secondButtonText: String = "B",
    thirdButtonText: String = "C",
    fourthButtonText: String = "D"
){
    Column(
        modifier = Modifier
            .padding(start = 20.dp, top = 5.dp, bottom = 20.dp, end = 20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { onButtonClick(firstButtonText) },
            modifier = Modifier
                .fillMaxWidth(),
            shape = CutCornerShape(10.dp)
        ) {
            Text(text = firstButtonText)
        }
        Button(
            onClick = { onButtonClick(secondButtonText) },
            modifier = Modifier
                .fillMaxWidth(),
            shape = CutCornerShape(10.dp)
        ) {
            Text(text = secondButtonText)
        }
        Button(
            onClick = { onButtonClick(thirdButtonText) },
            modifier = Modifier
                .fillMaxWidth(),
            shape = CutCornerShape(10.dp)
        ) {
            Text(text = thirdButtonText)
        }
        Button(
            onClick = { onButtonClick(fourthButtonText) },
            modifier = Modifier
                .fillMaxWidth(),
            shape = CutCornerShape(10.dp)
        ) {
            Text(text = fourthButtonText)
        }
    }
}

@Preview
@Composable
fun FourOptionButtonsPreview(){
    FourOptionButtons()
}