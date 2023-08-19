package com.example.sign_in.domain

import com.example.data.RemoteRepository

class SignInUserUseCase(val repository: RemoteRepository) {
    suspend fun execute(email: String, password: String) {
        repository.signInUser(email, password)
    }
}
