package com.example.data.di

import android.content.Context
import com.example.data.LocalRepository
import com.example.data.repositoryImpl.LocalRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideLocalRepository(context: Context): LocalRepository {
        return LocalRepositoryImpl(context)
    }
}
