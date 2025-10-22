package org.example.project.login.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.client.UserApiClient
import org.example.project.models.RegisterUserModel

class RegisterUserViewModel() : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun createUser(user: RegisterUserModel) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val createdUser = UserApiClient.createUser(user)
                if (createdUser != null) {
                    _isSuccess.value = true
                }
            } catch (e: Throwable) {
                println("Error from create-user: ${e.message}")
                // expose error state if needed
            } finally {
                _isLoading.value = false
            }
        }
    }
}
