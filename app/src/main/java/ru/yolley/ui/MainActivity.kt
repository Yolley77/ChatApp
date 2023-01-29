package ru.yolley.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Игнор статусбара
        //WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ChatApp()
        }
    }
}