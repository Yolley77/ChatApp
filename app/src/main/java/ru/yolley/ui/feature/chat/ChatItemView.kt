package ru.yolley.ui.feature.chat

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
internal fun ChatItem(
    text: String,
) {
    Card {
        Text(text = text)
    }
}