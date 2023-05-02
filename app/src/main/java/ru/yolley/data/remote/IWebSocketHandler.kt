package ru.yolley.data.remote

import kotlinx.coroutines.flow.SharedFlow

internal interface IWebSocketHandler {

    val messagesFlow: SharedFlow<String>

    suspend fun openConnection(userLogin: String)
    suspend fun sendMessage(message: String)
    suspend fun closeConnection()

}