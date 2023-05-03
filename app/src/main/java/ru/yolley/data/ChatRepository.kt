package ru.yolley.data

import kotlinx.coroutines.flow.SharedFlow
import ru.yolley.data.local.IDataStoreFacade
import ru.yolley.data.remote.IWebSocketHandler
import ru.yolley.domain.item.IChatItem
import javax.inject.Inject

internal class ChatRepository @Inject constructor(
    private val webSocketHandler: IWebSocketHandler,
    private val dataStoreFacade: IDataStoreFacade,
) : IChatRepository {

    override val messagesFlow: SharedFlow<IChatItem>
        get() = webSocketHandler.messagesFlow

    override suspend fun openConnection(userLogin: String) {
        webSocketHandler.openConnection(userLogin)
    }

    override suspend fun sendMessage(message: String) {
        webSocketHandler.sendMessage(message)
    }

    override suspend fun closeConnection() {
        webSocketHandler.closeConnection()
    }

    override suspend fun saveLogin(login: String) {
        dataStoreFacade.saveLogin(login)
    }

    override suspend fun getLogin(): String? = dataStoreFacade.getLogin()
    override suspend fun removeLogin() = dataStoreFacade.removeLogin()

}