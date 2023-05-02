package ru.yolley.data.local

internal interface IDataStoreFacade {

    suspend fun saveLogin(login: String)
    suspend fun getLogin(): String?
    suspend fun removeLogin()

}