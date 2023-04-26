package ru.yolley.ui.feature.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.yolley.data.IChatRepository
import javax.inject.Inject

@HiltViewModel
internal class AuthViewModel @Inject constructor(
    private val chatRepository: IChatRepository,
): ViewModel() {

    var userLogin: String by mutableStateOf("")
    var saveLoginState: Boolean by mutableStateOf(false)

    fun onUserLoginChanged(newLogin: String) {
        userLogin = newLogin
    }

    fun onSaveLoginChecked(checked: Boolean) {
        saveLoginState = checked
    }

    fun onLoginButtonClicked() {
        viewModelScope.launch {
            chatRepository.openConnection(userLogin)
        }
        // repo.sendEnterEvent
        // enterChat
    }

}