package ru.yolley.ui.feature.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.yolley.data.IChatRepository
import ru.yolley.domain.item.ChatTextMessage
import ru.yolley.domain.item.IChatItem
import ru.yolley.domain.item.MessageOwner
import ru.yolley.domain.state.IChatStateHolder
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
internal class ChatViewModel @Inject constructor(
    private val chatRepository: IChatRepository,
    private val chatStateHolder: IChatStateHolder,
) : ViewModel() {

    val chatItems: List<IChatItem>
        get() = chatStateHolder.chatItems

    var inputText: String by mutableStateOf("")

    fun onInputChanged(newInput: String) {
        inputText = newInput
    }

    fun onSendClicked() {
        if (inputText.isEmpty()) return
        chatStateHolder.chatItems.add(
            ChatTextMessage(
                id = UUID.randomUUID().toString(),
                text = inputText,
                owner = MessageOwner.USER
            )
        )
        viewModelScope.launch { chatRepository.sendMessage(inputText) }
        inputText = ""
    }

    fun onCloseConnection() {
        viewModelScope.launch { chatRepository.closeConnection() }
    }

}