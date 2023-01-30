package ru.yolley.ui.feature.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.yolley.R
import ru.yolley.ui.theme.ChatAppTheme

@Composable
internal fun ChatView(chatViewModel: ChatViewModel) {
    ChatView()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ChatView() {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Chat View")
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {

        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = "msg",
                onValueChange = {},
                singleLine = true,
                placeholder = {
                    Text(text = "Enter...")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(10.dp),
                trailingIcon = {
                    IconButton(onClick = { }) {
                        Icon(painter = painterResource(id = R.drawable.ic_send), contentDescription = "Отправить сообщение")
                    }
                },
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
        ChatView()
    }
}