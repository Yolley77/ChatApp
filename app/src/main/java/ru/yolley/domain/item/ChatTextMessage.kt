package ru.yolley.domain.item

import java.util.UUID

internal data class ChatTextMessage(
    override val id: String = UUID.randomUUID().toString(),
    override val text: String,
    override val owner: MessageOwner,
) : IChatItem
