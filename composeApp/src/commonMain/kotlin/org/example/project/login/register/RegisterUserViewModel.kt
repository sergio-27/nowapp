package org.example.project.login.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.client.UserApiClient
import org.example.project.models.User

class RegisterUserViewModel(
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main + Job())
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess.asStateFlow()

    private val _dbUser = MutableStateFlow<User?>(null)
    val dbUser: StateFlow<User?> = _dbUser.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()



    fun createUser(user: User) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val createdUser = UserApiClient.createUser(user)
                if (createdUser != null) {
                    _dbUser.value = createdUser
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

    fun reset() {
        _dbUser.value = null
        _isLoading.value = false
        _isSuccess.value = false
    }

    fun cancel() {
        scope.cancel()
        reset()
    }
}
