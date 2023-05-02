package ru.yolley.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject

internal class DataStoreFacade @Inject constructor(
    @ApplicationContext
    private val context: Context,
) : IDataStoreFacade {

    companion object {
        private const val PREFERENCES_NAME = "PREFERENCES_NAME"
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

        private const val LOGIN_KEY = "LOGIN"
    }

    override suspend fun saveLogin(login: String) {
        val key = stringPreferencesKey(LOGIN_KEY)
        context.dataStore.edit { preferences ->
            preferences[key] = login
        }
    }

    override suspend fun getLogin(): String? {
        val key = stringPreferencesKey(LOGIN_KEY)
        return context.dataStore.data.first()[key]
    }

    override suspend fun removeLogin() {
        val key = stringPreferencesKey(LOGIN_KEY)
        context.dataStore.edit { preferences ->
            preferences.remove(key)
        }
    }

}