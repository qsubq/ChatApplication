package com.example.on_boarding.onBoarding

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.on_boarding.onBoarding.domain.IsAlreadySeenOnBoardingUseCase
import com.example.on_boarding.onBoarding.domain.SetFlagSeenOnBoardingUseCase
import com.example.on_boarding.onBoarding.presentation.OnBoardingViewModel

class OnBoardingViewModelFactory(
    val context: Context,
    val setFlagSeenOnBoardingUseCase: SetFlagSeenOnBoardingUseCase,
    val isAlreadySeenOnBoardingUseCase: IsAlreadySeenOnBoardingUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OnBoardingViewModel(
            context as Application,
            setFlagSeenOnBoardingUseCase,
            isAlreadySeenOnBoardingUseCase,
        ) as T
    }
}
