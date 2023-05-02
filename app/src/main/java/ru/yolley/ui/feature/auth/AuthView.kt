package ru.yolley.ui.feature.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.yolley.ui.theme.ChatAppTheme
@Composable
internal fun AuthView(
    authViewModel: AuthViewModel,
    navigateToChat: () -> Unit,
) {
    AuthView(
        login = authViewModel.userLogin,
        saveLogin = authViewModel.saveLoginState,
        onSaveLoginChecked = authViewModel::onSaveLoginChecked,
        onUserLoginChanged = authViewModel::onUserLoginChanged,
        onLoginButtonClicked = {
            authViewModel.onLoginButtonClicked()
            navigateToChat.invoke()
        },
        modifier = Modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AuthView(
    login: String,
    saveLogin: Boolean,
    onSaveLoginChecked: (Boolean) -> Unit,
    onUserLoginChanged: (String) -> Unit,
    onLoginButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = login,
            maxLines = 1,
            onValueChange = onUserLoginChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 8.dp)
        ) {
            Checkbox(
                checked = saveLogin,
                onCheckedChange = onSaveLoginChecked,
            )
            Text(text = "Save login")
        }
        Button(
            onClick = onLoginButtonClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 64.dp)
        ) {
            Text(text = "Enter")
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun AuthPreview() {
    ChatAppTheme {
        AuthView(
            login = "login",
            saveLogin = true,
            onSaveLoginChecked = {},
            onUserLoginChanged = {},
            onLoginButtonClicked = {},
        )
    }
}