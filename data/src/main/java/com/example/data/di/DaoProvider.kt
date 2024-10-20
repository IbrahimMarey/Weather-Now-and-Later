package com.example.data.di

import android.content.Context
import com.example.data.local.AppDao
import com.example.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DaoProvider {
    @Provides
    fun provideAppDao(@ApplicationContext ctx: Context): AppDao {
        return AppDatabase.getInstance(ctx).appDao()
    }
}