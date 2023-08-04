package com.example.compose_oech.di

import android.content.Context
import com.example.compose_oech.MainActivity
import com.example.data.di.DataModule
import com.example.on_boarding.onBoarding.di.OnBoardingModule
import com.example.on_boarding.onBoarding.presentation.OnBoardingViewModel
import dagger.BindsInstance
import dagger.Component

@Component(modules = [OnBoardingModule::class, DataModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun getViewModel(): OnBoardingViewModel
}
