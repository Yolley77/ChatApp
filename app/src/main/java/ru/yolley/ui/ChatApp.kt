package ru.yolley.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.yolley.ui.feature.auth.AuthView
import ru.yolley.ui.feature.chat.ChatView
import ru.yolley.ui.feature.navigation.AppNavigationActions
import ru.yolley.ui.feature.navigation.AppScreen
import ru.yolley.ui.theme.ChatAppTheme

@Composable
fun ChatApp() {
    ChatAppTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            AppNavigationActions(navController)
        }

        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(
                navController = navController,
                startDestination = AppScreen.Auth.name
            ) {
                composable(AppScreen.Auth.name) {
                    AuthView(
                        authViewModel = hiltViewModel(),
                        navigateToChat = navigationActions.navigateToChat,
                    )
                }
                composable(AppScreen.Chat.name) {
                    ChatView(chatViewModel = hiltViewModel())
                }
            }
        }
    }
}