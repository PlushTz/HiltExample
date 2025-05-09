package com.example.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.BaseApplication
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

/**
 * Desc:
 * @author lijt
 * Created on 2023/5/10 23:37
 * Email:
 */
object DataStore {

    private val BaseApplication.dataStore: DataStore<Preferences> by preferencesDataStore(name = "amap_datastore")

    private val dataStore = BaseApplication.instance.dataStore

    /**
     * 存放Int数据
     */
    private suspend fun putIntData(key: String, value: Int) = dataStore.edit {
        it[intPreferencesKey(key)] = value
    }

    /**
     * 存放Long数据
     */
    private suspend fun putLongData(key: String, value: Long) = dataStore.edit {
        it[longPreferencesKey(key)] = value
    }

    /**
     * 存放String数据
     */
    private suspend fun putStringData(key: String, value: String) = dataStore.edit {
        it[stringPreferencesKey(key)] = value
    }

    /**
     * 存放Boolean数据
     */
    private suspend fun putBooleanData(key: String, value: Boolean) = dataStore.edit {
        it[booleanPreferencesKey(key)] = value
    }

    /**
     * 存放Float数据
     */
    private suspend fun putFloatData(key: String, value: Float) = dataStore.edit {
        it[floatPreferencesKey(key)] = value
    }

    /**
     * 存放Double数据
     */
    private suspend fun putDoubleData(key: String, value: Double) = dataStore.edit {
        it[doublePreferencesKey(key)] = value
    }

    /**
     * 取出Int数据
     */
    suspend fun getIntData(key: String, default: Int = 0): Int = coroutineScope {
        return@coroutineScope dataStore.data.map {
            it[intPreferencesKey(key)] ?: default
        }
            .first()
    }

    /**
     * 取出Long数据
     */
    suspend fun getLongData(key: String, default: Long = 0): Long = coroutineScope {
        return@coroutineScope dataStore.data.map {
            it[longPreferencesKey(key)] ?: default
        }
            .first()
    }

    /**
     * 取出String数据
     */
    suspend fun getStringData(key: String, default: String? = null): String = coroutineScope {
        return@coroutineScope dataStore.data.map {
            it[stringPreferencesKey(key)] ?: default
        }
            .first()!!
    }

    /**
     * 取出Boolean数据
     */
    suspend fun getBooleanData(key: String, default: Boolean = false): Boolean = coroutineScope {
        return@coroutineScope dataStore.data.map {
            it[booleanPreferencesKey(key)] ?: default
        }
            .first()
    }

    /**
     * 取出Float数据
     */
    suspend fun getFloatData(key: String, default: Float = 0.0f): Float = coroutineScope {
        return@coroutineScope dataStore.data.map {
            it[floatPreferencesKey(key)] ?: default
        }
            .first()
    }

    /**
     * 取出Double数据
     */
    suspend fun getDoubleData(key: String, default: Double = 0.00): Double = coroutineScope {
        return@coroutineScope dataStore.data.map {
            it[doublePreferencesKey(key)] ?: default
        }
            .first()
    }

    /**
     * 存数据
     */
    suspend fun <T> putData(key: String, value: T) {
        coroutineScope {
            when (value) {
                is Int -> putIntData(key, value)
                is Long -> putLongData(key, value)
                is String -> putStringData(key, value)
                is Boolean -> putBooleanData(key, value)
                is Float -> putFloatData(key, value)
                is Double -> putDoubleData(key, value)
                else -> throw IllegalArgumentException("This type cannot be saved to the Data Store")
            }
        }
    }

    /**
     * 取数据
     */
    suspend inline fun <reified T> getData(key: String, defaultValue: T): T {
        val data = when (defaultValue) {
            is Int -> getIntData(key, defaultValue)
            is Long -> getLongData(key, defaultValue)
            is String -> getStringData(key, defaultValue)
            is Boolean -> getBooleanData(key, defaultValue)
            is Float -> getFloatData(key, defaultValue)
            is Double -> getDoubleData(key, defaultValue)
            else -> throw IllegalArgumentException("This type cannot be saved to the Data Store")
        }
        return data as T
    }

    /**
     * 清空数据
     */
    suspend fun clearData() = coroutineScope { dataStore.edit { it.clear() } }
}