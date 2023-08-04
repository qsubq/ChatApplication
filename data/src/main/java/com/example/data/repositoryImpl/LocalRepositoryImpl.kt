package com.example.data.repositoryImpl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_settings")
class LocalRepositoryImpl(val context: Context) : LocalRepository {

    private val seenOnBoardingKey = booleanPreferencesKey("isOnBoardingSeenUseCase")

    override suspend fun setFlagSeenOnBoarding() {
        context.dataStore.edit { settings ->
            settings[seenOnBoardingKey] = true
        }
    }

    override fun isAlreadySeenOnBoarding(): Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[seenOnBoardingKey] ?: false
        }
}
