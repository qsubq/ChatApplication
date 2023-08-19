package com.example.sign_in.di

import android.app.Application
import android.content.Context
import com.example.data.RemoteRepository
import com.example.sign_in.domain.SignInUserUseCase
import com.example.sign_in.presentation.SignInViewModel
import dagger.Module
import dagger.Provides

@Module
class SignInModule {

    @Provides
    fun provideSignInUserUseCase(repository: RemoteRepository): SignInUserUseCase {
        return SignInUserUseCase(repository)
    }

    @Provides
    fun provideViewModel(
        context: Context,
        signInUserUseCase: SignInUserUseCase,
    ): SignInViewModel =
        SignInViewModel(context as Application, signInUserUseCase)
}
