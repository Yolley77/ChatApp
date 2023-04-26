package ru.yolley.data

import android.util.Log
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChatRepository @Inject constructor() : IChatRepository {

    private val client = HttpClient {
        install(WebSockets)
    }


    // TODO Отдельная отправка сообщений в один вебсокет
    // TODO Обработка kotlinx.coroutines.channels.ClosedReceiveChannelException: Channel was closed
    override suspend fun openConnection(userLogin: String) = withContext(Dispatchers.IO) {
        client.webSocket(
            method = HttpMethod.Get,
            //host = "127.0.0.1",
            host = "10.0.2.2", // emulator
            port = 8080,
            path = "/chat"
        ) {
            send(userLogin)
            while(true) {
                val othersMessage = incoming.receive() as? Frame.Text ?: continue
                Log.d("DEBUGG", othersMessage.readText())
            }
        }
    }

    override fun closeConnection() {
        client.close()
        Log.d("DEBUGG","Connection closed. Goodbye!")
    }

}