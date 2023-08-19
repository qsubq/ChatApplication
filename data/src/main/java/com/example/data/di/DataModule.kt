package com.example.data.di

import android.content.Context
import com.example.data.LocalRepository
import com.example.data.RemoteRepository
import com.example.data.repositoryImpl.LocalRepositoryImpl
import com.example.data.repositoryImpl.RemoteRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideLocalRepository(context: Context): LocalRepository {
        return LocalRepositoryImpl(context)
    }

    @Provides
    fun provideRemoteRepository(): RemoteRepository {
        return RemoteRepositoryImpl()
    }
}
