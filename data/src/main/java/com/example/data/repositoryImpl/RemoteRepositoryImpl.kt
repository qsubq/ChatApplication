package com.example.data.repositoryImpl

import com.example.data.RemoteRepository
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime

class RemoteRepositoryImpl : RemoteRepository {
    val client = createSupabaseClient(
        supabaseUrl = "https://oywoqjqzcwhfebnxmlri.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im95d29xanF6Y3doZmVibnhtbHJpIiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTE3NDY5NzUsImV4cCI6MjAwNzMyMjk3NX0.2IDQLMvehoKV5u6YN-2PHvQ1TDeBeOeGmY75CgOzLVc",
    ) {
        install(GoTrue)
        install(Postgrest)
        install(Realtime)
    }

    override suspend fun signUpUser(name: String, phone: Int, email: String, password: String) {
        val user = client.gotrue.signUpWith(Email) {
            this.email = email
            this.password = password
        }
    }

    override suspend fun signInUser(email: String, password: String) {
        client.gotrue.loginWith(Email) {
            this.email = email
            this.password = password
        }
    }
}
