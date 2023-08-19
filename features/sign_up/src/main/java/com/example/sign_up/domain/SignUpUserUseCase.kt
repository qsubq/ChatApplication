package com.example.sign_up.domain

import com.example.data.RemoteRepository

class SignUpUserUseCase(val repository: RemoteRepository) {
    suspend fun execute(name: String, phone: Int, email: String, password: String) {
        repository.signUpUser(name, phone, email, password)
    }
}
