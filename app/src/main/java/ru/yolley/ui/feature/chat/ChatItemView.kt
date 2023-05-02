package ru.yolley.ui.feature.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.yolley.ui.feature.chat.item.ChatTextMessage
import ru.yolley.ui.feature.chat.item.IChatUIItem
import ru.yolley.ui.feature.chat.item.MessageOwner
import ru.yolley.ui.theme.DirtyWhite
import ru.yolley.ui.theme.LightGray
import ru.yolley.ui.theme.Orange

@Composable
internal fun ChatItem(
    item: IChatUIItem
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        when {
            item !is ChatTextMessage -> return
            item.owner == MessageOwner.SYSTEM -> ChatSystemItem(text = item.text)
            item.owner == MessageOwner.USER -> ChatUserItem(text = item.text)
            item.owner == MessageOwner.ANOTHER -> ChatAnotherItem(text = item.text)
        }
    }
}

@Composable
internal fun BoxScope.ChatSystemItem(
    text: String
) {
    Text(
        text = text,
        modifier = Modifier
            .align(Alignment.Center)
            .padding(horizontal = 8.dp)
            .wrapContentWidth()
    )
}

@Composable
internal fun BoxScope.ChatUserItem(
    text: String
) {
    Card(
        colors = CardDefaults.cardColors(Orange),
        modifier = Modifier
            .align(Alignment.CenterEnd)
            //.padding(top = 16.dp)
            .wrapContentHeight()
            .fillMaxWidth(fraction = 0.8f),
    ) {
        Text(
            text = text,
            color = LightGray,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
internal fun BoxScope.ChatAnotherItem(
    text: String
) {
    Card(
        colors = CardDefaults.cardColors(LightGray),
        modifier = Modifier
            .align(Alignment.CenterStart)
            //.padding(top = 16.dp)
            .wrapContentHeight()
            .fillMaxWidth(fraction = 0.8f),
    ) {
        Text(
            text = text,
            color = DirtyWhite,
            modifier = Modifier.padding(8.dp)
        )
    }
}