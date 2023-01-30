package ru.yolley.ui.feature.chat.item

data class ChatTextMessage(
    override val id: String,
    override val text: String,
) : IChatUIItem
