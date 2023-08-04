package com.example.data

import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun setFlagSeenOnBoarding()
    fun isAlreadySeenOnBoarding(): Flow<Boolean>
}
