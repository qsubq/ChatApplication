package com.example.on_boarding.onBoarding.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.on_boarding.onBoarding.domain.IsAlreadySeenOnBoardingUseCase
import com.example.on_boarding.onBoarding.domain.SetFlagSeenOnBoardingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class OnBoardingViewModel(
    context: Application,
    val setFlagSeenOnBoardingUseCase: SetFlagSeenOnBoardingUseCase,
    val isAlreadySeenOnBoardingUseCase: IsAlreadySeenOnBoardingUseCase,
) : AndroidViewModel(context) {
    val isAlreadySeenLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun setFlagSeenOnBoarding() = viewModelScope.launch { setFlagSeenOnBoardingUseCase.execute() }
    suspend fun isAlreadySeenOnBoarding() {
        viewModelScope.launch {
            isAlreadySeenOnBoardingUseCase.execute().collect {
                isAlreadySeenLiveData.value = it
            }
        }
    }
}
