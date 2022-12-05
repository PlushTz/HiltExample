package com.example.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

/**
 * Desc:
 * @author lijt
 * Created on 2022/11/29 9:17
 * Email: lijt@eetrust.com
 */
// At the top level of your kotlin file:
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreUtil(private val context: Context) {


    companion object {
        private var instance: DataStoreUtil? = null
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: DataStoreUtil(context).also {
                instance = it
            }
        }
    }

    fun setInt(key: String) {
        val KEY = intPreferencesKey(key)
        context.dataStore.data.map { preferences ->
            preferences[KEY] ?: 0
        }
    }
}