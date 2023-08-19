package com.example.sign_in.presentation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sign_in.domain.SignInUserUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class SignInViewModel(val context: Application, val signInUserUseCase: SignInUserUseCase) :
    AndroidViewModel(context) {

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("SignUpScreen", throwable.message.toString())
    }

    fun signInUser(email: String, password: String) {
        if (isOnline(context)) {
            viewModelScope.launch(errorHandler) {
                signInUserUseCase.execute(email, password)
            }
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
