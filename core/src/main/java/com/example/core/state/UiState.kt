package com.example.core.state

sealed class UiState {
    object Loading : UiState()
    object Success : UiState()
    object Waiting : UiState()
    data class Error(val errorTitle: String, val errorDescription: String) : UiState()
}
