package com.example.dagger_hilt_mvvm_room.di

import android.app.Application
import android.content.Context
import com.example.dagger_hilt_mvvm_room.db.AppDao
import com.example.dagger_hilt_mvvm_room.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getAppDB(context: Application): AppDatabase {
        return AppDatabase.getAppDB(context)
    }

    @Singleton
    @Provides
    fun getDao(appDatabase: AppDatabase): AppDao{
        return appDatabase.getDao()
    }

}