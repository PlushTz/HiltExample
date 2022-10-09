package com.example.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.database.ExampleDatabase
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
class DataBaseModules {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ExampleDatabase {
        return Room.databaseBuilder(context, ExampleDatabase::class.java, "user.db").build()
    }
}