package com.example.quizrank.util

import com.example.quizrank.ui.model.UiText

sealed class UiEvent {
    object Success: UiEvent()

    data class Failure(val message: UiText): UiEvent()
}

