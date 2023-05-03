package ru.yolley.domain.state

import ru.yolley.domain.item.IChatItem

internal interface IChatStateHolder {

    val chatItems: MutableList<IChatItem>

}