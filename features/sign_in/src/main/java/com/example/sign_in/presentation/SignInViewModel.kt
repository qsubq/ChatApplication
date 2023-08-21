package com.example.sign_in.presentation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.state.UiState
import com.example.sign_in.domain.SignInUserUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignInViewModel(val context: Application, private val signInUserUseCase: SignInUserUseCase) :
    AndroidViewModel(context) {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Waiting)
    var uiState: StateFlow<UiState> = _uiState

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("SignUpScreen", throwable.message.toString())
        _uiState.value = UiState.Error(throwable.message.toString(), throwable.cause.toString())
    }

    fun signInUser(email: String, password: String) {
        _uiState.value = UiState.Loading

        if (isOnline(context)) {
            viewModelScope.launch(errorHandler) {
                signInUserUseCase.execute(email, password)
                _uiState.value = UiState.Success
            }
        } else {
            _uiState.value = UiState.Error("Out of connection", "Check your network")
        }
    }

    private fun isOnline(context: Application): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return networkCapabilities != null
    }
}
