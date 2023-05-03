package ru.yolley.domain.item

internal interface IChatItem {
    val id: String
    val text: String
    val owner: MessageOwner
}

internal enum class MessageOwner {
    SYSTEM, USER, ANOTHER,
}