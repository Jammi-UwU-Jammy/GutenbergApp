package com.vivich.starlitapp.globalhandler

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")
val BRIGHTNESS_KEY = floatPreferencesKey("brightness")

suspend fun saveBrightness(context: Context, brightness: Float) {
    context.dataStore.edit { preferences ->
        preferences[BRIGHTNESS_KEY] = brightness
    }
}

private fun getTBrightness(context: Context): Flow<Float> {
    return context.dataStore.data.map { preferences ->
        preferences[BRIGHTNESS_KEY] ?: 0.5f
    }
}