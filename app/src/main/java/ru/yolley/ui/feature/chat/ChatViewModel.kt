package ru.yolley.ui.feature.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.yolley.data.IChatRepository
import ru.yolley.ui.feature.chat.item.ChatTextMessage
import ru.yolley.ui.feature.chat.item.IChatUIItem
import ru.yolley.ui.feature.chat.item.MessageOwner
import javax.inject.Inject

@HiltViewModel
internal class ChatViewModel @Inject constructor(
    private val chatRepository: IChatRepository,
) : ViewModel() {

    init {
        viewModelScope.launch {
            chatRepository.messagesFlow.collect {
                chatItems.add(
                    ChatTextMessage("out", it, MessageOwner.ANOTHER),
                )
            }
        }
    }

    var chatItems: MutableList<IChatUIItem> = mutableStateListOf(
        ChatTextMessage("1", "История сообщений не сохраняется", MessageOwner.SYSTEM),
    )

    var inputText: String by mutableStateOf("")

    fun onInputChanged(newInput: String) {
        inputText = newInput
    }

    fun onSendClicked() {
        if (inputText.isEmpty()) return
        chatItems.add(ChatTextMessage("123", inputText, MessageOwner.USER))
        viewModelScope.launch { chatRepository.sendMessage(inputText) }
        inputText = ""
    }

    fun onCloseConnection() {
        viewModelScope.launch { chatRepository.closeConnection() }
    }

}