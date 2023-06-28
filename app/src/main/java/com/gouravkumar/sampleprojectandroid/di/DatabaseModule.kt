package com.gouravkumar.sampleprojectandroid.di

import android.content.Context
import com.gouravkumar.sampleprojectandroid.local.AppDatabase
import com.gouravkumar.sampleprojectandroid.local.dao.UsersListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun providesUserListDao(@ApplicationContext appContext : Context) : UsersListDao {
        return AppDatabase.getInstance(appContext).usersListDao
    }
}