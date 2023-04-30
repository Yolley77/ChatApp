package ru.yolley.data

import android.util.Log
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChatRepository @Inject constructor() : IChatRepository {

    private val client = HttpClient {
        install(WebSockets)
    }

    private var session: DefaultClientWebSocketSession? = null

    override var messagesFlow: MutableSharedFlow<String> = MutableSharedFlow()

    // TODO Отдельная отправка сообщений в один вебсокет
    // TODO Обработка kotlinx.coroutines.channels.ClosedReceiveChannelException: Channel was closed
    override suspend fun openConnection(userLogin: String) = withContext(Dispatchers.IO) {
        session = client.webSocketSession(
            method = HttpMethod.Get,
            //host = "127.0.0.1",
            //host  = "10.0.2.2", // emulator

            port = 8080,
            path = "/chat"
        )
        session?.run {
            send("$userLogin connected")
            launch {
                while (true) {
                    val othersMessage = incoming.receive() as? Frame.Text ?: continue
                    val message = othersMessage.readText()
                    messagesFlow.emit(message)
                    Log.d("DEBUGG", message)
                }
            }
        }
        Unit
    }

    override suspend fun sendMessage(message: String) {
        session?.send(message)
    }

    override fun closeConnection() {
        client.close()
        Log.d("DEBUGG", "Connection closed. Goodbye!")
    }

}