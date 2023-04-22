package ru.yolley.ui.feature.chat.item

internal data class ChatTextMessage(
    override val id: String,
    override val text: String,
    override val owner: MessageOwner,
) : IChatUIItem
