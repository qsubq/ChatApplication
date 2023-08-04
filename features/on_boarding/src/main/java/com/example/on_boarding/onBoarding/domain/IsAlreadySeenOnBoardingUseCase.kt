package com.example.on_boarding.onBoarding.domain

import com.example.data.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsAlreadySeenOnBoardingUseCase(val repository: LocalRepository) {
    fun execute(): Flow<Boolean> {
        return repository.isAlreadySeenOnBoarding()
    }
}
