package com.yuhdeveloper.cleanarchitecturesample.feature_news.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.datastore: DataStore<Preferences> by preferencesDataStore("user")

class DataStoreManager(val context: Context) {

    private val myDatastore: DataStore<Preferences> = context.datastore

    companion object {
        val USER_USERNAME = stringPreferencesKey("USERNAME")
        val USER_EMAIL = stringPreferencesKey("EMAIL")
        val USER_PASSWORD = stringPreferencesKey("PASSWORD")
        val USER_TOKEN = stringPreferencesKey("TOKEN")
        val USER_IS_AUTH = booleanPreferencesKey("IS_AUTH")
    }

    suspend fun storeProductData(
        username: String,
        email: String,
        token: String,
        password: String,
        isAuth: Boolean
    ) {
        myDatastore.edit { preferences ->
            preferences[USER_USERNAME] = username
            preferences[USER_EMAIL] = email
            preferences[USER_PASSWORD] = password
            preferences[USER_TOKEN] = token
            preferences[USER_IS_AUTH] = isAuth
        }
    }

    val userIsAuth: Flow<Boolean> = myDatastore.data.map {
        it[USER_IS_AUTH] ?: false
    }
}