package ru.yolley.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ru.yolley.ui.feature.auth.AuthView
import ru.yolley.ui.theme.ChatAppTheme

@Composable
fun ChatApp() {
    ChatAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AuthView(authViewModel = hiltViewModel())
        }
    }
}