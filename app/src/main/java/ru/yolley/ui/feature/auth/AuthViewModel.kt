package ru.yolley.ui.feature.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class AuthViewModel @Inject constructor(): ViewModel() {

    var userLogin: String by mutableStateOf("")
    var saveLoginState: Boolean by mutableStateOf(false)

    fun onUserLoginChanged(newLogin: String) {
        userLogin = newLogin
    }

    fun onSaveLoginChecked(checked: Boolean) {
        saveLoginState = checked
    }

    fun onLoginButtonClicked() {
        // repo.sendEnterEvent
        // enterChat
    }

}