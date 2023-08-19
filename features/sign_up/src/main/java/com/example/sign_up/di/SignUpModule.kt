package com.example.sign_up.di

import android.app.Application
import android.content.Context
import com.example.data.RemoteRepository
import com.example.sign_up.domain.SignUpUserUseCase
import com.example.sign_up.presentation.SignUpViewModel
import dagger.Module
import dagger.Provides

@Module
class SignUpModule {

    @Provides
    fun provideSignUpUserUseCase(remoteRepository: RemoteRepository): SignUpUserUseCase {
        return SignUpUserUseCase(remoteRepository)
    }

    @Provides
    fun provideViewModel(
        context: Context,
        signUpUserUseCase: SignUpUserUseCase,
    ): SignUpViewModel =
        SignUpViewModel(context as Application, signUpUserUseCase)
}
