package com.example.on_boarding.onBoarding.domain

import com.example.data.LocalRepository
import javax.inject.Inject

class SetFlagSeenOnBoardingUseCase(val repository: LocalRepository) {

    suspend fun execute() {
        repository.setFlagSeenOnBoarding()
    }
}
