package com.example.datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.lang.Exception

/**
 * Desc:
 * @author lijt
 * Created on 2022/12/13 10:41
 * Email: lijt@eetrust.com
 */
class DataStoreUtil(private val context: Context) {
    companion object {
        private var instance: DataStoreUtil? = null
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: DataStoreUtil(context).also {
                instance = it
            }
        }
    }

    /**
     * 写入数据
     * @param preferencesKey [Preferences.Key]
     * [intPreferencesKey] [doublePreferencesKey] [stringPreferencesKey]
     * [booleanPreferencesKey] [floatPreferencesKey] [longPreferencesKey]
     * [stringSetPreferencesKey]
     * @param value T
     */
    suspend fun <T> write(preferencesKey: Preferences.Key<T>, value: T) {
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    /**
     * 读取数据
     * @param preferencesKey Key<T> [Preferences.Key]
     * [intPreferencesKey] [doublePreferencesKey] [stringPreferencesKey]
     * [booleanPreferencesKey] [floatPreferencesKey] [longPreferencesKey]
     * [stringSetPreferencesKey]
     * @return T?
     */
    suspend fun <T> read(preferencesKey: Preferences.Key<T>): T? {
        return context.dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
                preferences[preferencesKey]
            }
            .first()
    }
}