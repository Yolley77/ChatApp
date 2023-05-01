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
            //host = "192.168.2.135",
            host = "77.91.196.***",
            port = 8080,
            path = "/chat"
        )
        session?.run {
            send("$userLogin connected!")
            launch {
                try {
                    for (message in incoming) {
                        message as? Frame.Text ?: continue
                        val text = message.readText()
                        messagesFlow.emit(text)
                        Log.d("DEBUGG", text)
                    }
                } catch (e: Exception) {
                    println("Error while receiving: " + e.localizedMessage)
                }
            }
        }
        Unit
    }

    override suspend fun sendMessage(message: String) {
        try {
            session?.send(message)
        } catch (e: Exception) {
            println("Error while sending: " + e.localizedMessage)
        }
    }

    override suspend fun closeConnection() {
        session?.send("disconnecting...")
        session?.close()
        session = null
        Log.d("DEBUGG", "Connection closed. Goodbye!")
    }

}