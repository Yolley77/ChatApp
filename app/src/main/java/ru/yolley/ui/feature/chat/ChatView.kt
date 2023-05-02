package ru.yolley.ui.feature.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.launch
import ru.yolley.R
import ru.yolley.ui.ComposableLifecycle
import ru.yolley.ui.feature.chat.item.IChatUIItem
import ru.yolley.ui.theme.ChatAppTheme

@Composable
internal fun ChatView(chatViewModel: ChatViewModel) {
    ChatView(
        items = chatViewModel.chatItems,
        inputText = chatViewModel.inputText,
        onInputChanged = chatViewModel::onInputChanged,
        onSendClicked = chatViewModel::onSendClicked,
        onCloseConnection = chatViewModel::onCloseConnection,
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
internal fun ChatView(
    items: List<IChatUIItem>,
    inputText: String,
    onInputChanged: (String) -> Unit,
    onSendClicked: () -> Unit,
    onCloseConnection: () -> Unit,
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    ComposableLifecycle(LocalLifecycleOwner.current) { _, event ->
        if (event == Lifecycle.Event.ON_STOP) {
            onCloseConnection.invoke()
        }
    }

    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(bottom = 4.dp)
            .fillMaxSize()
    ) {
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Bottom),
            modifier = Modifier
                .weight(1f)
        ) {
            items(items.size) {
                ChatItem(item = items[it])
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = onInputChanged,
                maxLines = 2,
                placeholder = {
                    Text(text = "Сообщение...")
                },
                modifier = Modifier.weight(1f),
                shape = CircleShape,
                trailingIcon = {
                    IconButton(
                        modifier = Modifier,
                        onClick = {
                            coroutineScope.launch {
                                listState.animateScrollToItem(index = items.lastIndex)
                            }
                            keyboardController?.hide()
                            onSendClicked.invoke()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_send),
                            contentDescription = "Отправить сообщение"
                        )
                    }
                }
            )

        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ChatPreview() {
    ChatAppTheme {
        ChatView(
            items = listOf(),
            inputText = "",
            onInputChanged = {},
            onSendClicked = {},
            onCloseConnection = {},
        )
    }
}