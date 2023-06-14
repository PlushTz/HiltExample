package com.example.di

import android.content.Context
import androidx.room.Room
import com.example.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/9 10:06
 * Email: lijt@eetrust.com
 */
@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModules {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_database.db").build()
    }
}