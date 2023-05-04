package ru.yolley.data.remote

import android.util.Log
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.yolley.domain.item.ChatTextMessage
import ru.yolley.domain.item.IChatItem
import ru.yolley.domain.item.MessageOwner
import javax.inject.Inject

internal class WebSocketHandler @Inject constructor() : IWebSocketHandler {

    private val client = HttpClient {
        install(WebSockets)
    }

    private var session: DefaultClientWebSocketSession? = null

    override var messagesFlow: MutableSharedFlow<IChatItem> = MutableSharedFlow()

    override suspend fun openConnection(userLogin: String) = withContext(Dispatchers.IO) {
        session = client.webSocketSession(
            method = HttpMethod.Get,
            //host = "127.0.0.1",
            //host  = "10.0.2.2", // emulator
            //host = "192.168.2.135",
            host = "77.91.196.236",
            port = 8080,
            path = "/chat"
        )
        session?.run {
            // system
            send(userLogin)
            launch {
                try {
                    for (message in incoming) {
                        message as? Frame.Text ?: continue
                        val text = message.readText()
                        when  {
                            !text.startsWith('[') -> {
                                messagesFlow.emit(
                                    ChatTextMessage(
                                        text = text,
                                        owner = MessageOwner.SYSTEM
                                    )
                                )
                            }
                            text.startsWith("[$userLogin]") -> {
                                // delivered
                            }
                            else -> {
                                messagesFlow.emit(
                                    ChatTextMessage(
                                        text = text,
                                        owner = MessageOwner.ANOTHER
                                    )
                                )
                            }
                        }
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
        // system
        session?.close()
        session = null
        Log.d("DEBUGG", "Connection closed. Goodbye!")
    }

}