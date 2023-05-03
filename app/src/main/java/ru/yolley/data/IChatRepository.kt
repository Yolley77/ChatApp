package ru.yolley.data

import kotlinx.coroutines.flow.SharedFlow
import ru.yolley.domain.item.IChatItem

internal interface IChatRepository {

    val messagesFlow: SharedFlow<IChatItem>

    suspend fun openConnection(userLogin: String)
    suspend fun sendMessage(message: String)
    suspend fun closeConnection()
    suspend fun saveLogin(login: String)
    suspend fun getLogin(): String?
    suspend fun removeLogin()


}