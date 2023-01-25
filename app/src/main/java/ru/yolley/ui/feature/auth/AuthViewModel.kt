package ru.yolley.ui.feature.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class AuthViewModel @Inject constructor(): ViewModel() {

    val userLogin: String? by mutableStateOf(null)

    fun onSaveLoginChecked(checked: Boolean) {
        if (checked) ""
        else ""
    }

    fun onLoginButtonClicked() {
        // empty now
    }

}