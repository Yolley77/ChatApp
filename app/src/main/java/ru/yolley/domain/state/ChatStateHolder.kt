package ru.yolley.domain.state

import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.yolley.data.IChatRepository
import ru.yolley.domain.item.ChatTextMessage
import ru.yolley.domain.item.IChatItem
import ru.yolley.domain.item.MessageOwner
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class ChatStateHolder @Inject constructor(
    private val chatRepository: IChatRepository,
) : IChatStateHolder, CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    init {
        launch {
            chatRepository.messagesFlow.collect {
                chatItems.add(it)
            }
        }
    }

    override val chatItems: MutableList<IChatItem> = mutableStateListOf(
        ChatTextMessage(
            id = UUID.randomUUID().toString(),
            text = "История сообщений не сохраняется",
            owner = MessageOwner.SYSTEM
        ),
    )

}