package com.example.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

/**
 * Desc:
 * @author lijt
 * Created on 2022/12/13 11:05
 * Email: lijt@eetrust.com
 */
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")