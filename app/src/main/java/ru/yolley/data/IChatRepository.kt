package ru.yolley.data

interface IChatRepository {

    suspend fun openConnection(userLogin: String)
    fun closeConnection()

}