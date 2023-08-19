package com.example.data

interface RemoteRepository {
    suspend fun signUpUser(name: String, phone: Int, email: String, password: String)
    suspend fun signInUser(email: String, password: String)
}
