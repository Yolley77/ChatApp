package ru.yolley.data.remote

import kotlinx.coroutines.flow.SharedFlow
import ru.yolley.domain.item.IChatItem

internal interface IWebSocketHandler {

    val messagesFlow: SharedFlow<IChatItem>

    suspend fun openConnection(userLogin: String)
    suspend fun sendMessage(message: String)
    suspend fun closeConnection()

}