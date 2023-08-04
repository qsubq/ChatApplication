package com.example.on_boarding.onBoarding.di

import android.app.Application
import android.content.Context
import com.example.data.LocalRepository
import com.example.on_boarding.onBoarding.OnBoardingViewModelFactory
import com.example.on_boarding.onBoarding.domain.IsAlreadySeenOnBoardingUseCase
import com.example.on_boarding.onBoarding.domain.SetFlagSeenOnBoardingUseCase
import com.example.on_boarding.onBoarding.presentation.OnBoardingViewModel
import dagger.Module
import dagger.Provides

@Module
class OnBoardingModule {

    @Provides
    fun provideOnBoardingViewModelFactory(
        context: Context,
        isAlreadySeenOnBoardingUseCase: IsAlreadySeenOnBoardingUseCase,
        setFlagSeenOnBoardingUseCase: SetFlagSeenOnBoardingUseCase,
    ): OnBoardingViewModelFactory {
        return OnBoardingViewModelFactory(
            context,
            setFlagSeenOnBoardingUseCase,
            isAlreadySeenOnBoardingUseCase,
        )
    }

    @Provides
    fun provideIsAlreadySeenOnBoardingUseCase(repository: LocalRepository): IsAlreadySeenOnBoardingUseCase {
        return IsAlreadySeenOnBoardingUseCase(repository)
    }

    @Provides
    fun provideSetFlagSeenOnBoardingUseCase(repository: LocalRepository): SetFlagSeenOnBoardingUseCase {
        return SetFlagSeenOnBoardingUseCase(repository)
    }

    @Provides
    fun provideViewModel(
        context: Context,
        isAlreadySeenOnBoardingUseCase: IsAlreadySeenOnBoardingUseCase,
        setFlagSeenOnBoardingUseCase: SetFlagSeenOnBoardingUseCase,
    ): OnBoardingViewModel =
        OnBoardingViewModel(
            context as Application,
            setFlagSeenOnBoardingUseCase,
            isAlreadySeenOnBoardingUseCase,
        )
}
