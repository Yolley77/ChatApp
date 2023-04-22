package ru.yolley.ui.feature.chat.item

internal interface IChatUIItem {
    val id: String
    val text: String
    val owner: MessageOwner
}

internal enum class MessageOwner {
    SYSTEM, USER, ANOTHER,
}