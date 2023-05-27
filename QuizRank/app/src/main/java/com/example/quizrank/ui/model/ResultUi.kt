package com.example.quizrank.ui.model

import com.example.quizrank.domain.model.Result

data class ResultUi(
    val id: String = "",
    val topic: String = "",
    val result: Int = 0,
    val name: String = ""
)

fun Result.asResultUi(): ResultUi = ResultUi(
    id = id,
    topic = topic,
    result = result,
    name = name
)

fun ResultUi.asResult(): Result = Result(
    id = id,
    topic = topic,
    result = result,
    name = name
)