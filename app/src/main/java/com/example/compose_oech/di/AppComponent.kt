package com.example.compose_oech.di

import android.content.Context
import com.example.compose_oech.MainActivity
import com.example.data.di.DataModule
import com.example.on_boarding.onBoarding.di.OnBoardingModule
import com.example.on_boarding.onBoarding.presentation.OnBoardingViewModel
import com.example.sign_in.di.SignInModule
import com.example.sign_in.presentation.SignInViewModel
import com.example.sign_up.di.SignUpModule
import com.example.sign_up.presentation.SignUpViewModel
import dagger.BindsInstance
import dagger.Component

@Component(modules = [SignUpModule::class, SignInModule::class, OnBoardingModule::class, DataModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun getOnBoardingViewModel(): OnBoardingViewModel
    fun getSignUpViewModel(): SignUpViewModel
    fun getSignInViewModel(): SignInViewModel
}
