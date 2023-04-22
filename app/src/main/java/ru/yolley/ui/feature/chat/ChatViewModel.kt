package ru.yolley.ui.feature.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.yolley.ui.feature.chat.item.ChatTextMessage
import ru.yolley.ui.feature.chat.item.IChatUIItem
import ru.yolley.ui.feature.chat.item.MessageOwner
import javax.inject.Inject

@HiltViewModel
internal class ChatViewModel @Inject constructor() : ViewModel() {

    var chatItems: MutableList<IChatUIItem> = mutableStateListOf(
            ChatTextMessage("1", "Message first", MessageOwner.SYSTEM),
            ChatTextMessage("2", "Message second", MessageOwner.USER),
            ChatTextMessage("3", "Message third", MessageOwner.ANOTHER),
    )

    var inputText: String by mutableStateOf("")

    fun onInputChanged(newInput: String) {
        inputText = newInput
    }

    fun onSendClicked() {
        if (inputText.isEmpty()) return
        chatItems.add(ChatTextMessage("123", inputText, MessageOwner.USER))
        inputText = ""
    }

}