package ru.yolley.ui.feature.navigation

import androidx.navigation.NavController

internal class AppNavigationActions(
    navController: NavController
) {
    val navigateToChat: () -> Unit = {
        navController.navigate(AppScreen.Chat.name)
    }

}