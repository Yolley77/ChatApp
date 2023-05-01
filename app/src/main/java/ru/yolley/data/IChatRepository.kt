package ru.yolley.data

import kotlinx.coroutines.flow.SharedFlow

interface IChatRepository {

    val messagesFlow: SharedFlow<String>

    suspend fun openConnection(userLogin: String)
    suspend fun sendMessage(message: String)
    suspend fun closeConnection()

}