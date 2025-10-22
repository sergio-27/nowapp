package org.example.project.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.client.AuthorizationApiClient
import org.example.project.client.UserApiClient
import org.example.project.models.AuthenticatedUser
import org.example.project.models.User
import org.example.project.utils.AppConstants

class TransactionViewModel(
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main + Job())
) : ViewModel() {
    private val _qrBase64 = MutableStateFlow<String?>(null)
    val qrBase64: StateFlow<String?> = _qrBase64

    private val _qrUrl = MutableStateFlow<String?>(null)
    val qrUrl: StateFlow<String?> = _qrUrl

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess.asStateFlow()

    private val _authenticatedUserCredential = MutableStateFlow<AuthenticatedUser?>(null)
    val authenticatedUserCredential: StateFlow<AuthenticatedUser?> = _authenticatedUserCredential.asStateFlow()

    private val _dbUser = MutableStateFlow<User?>(null)
    val dbUser: StateFlow<User?> = _dbUser.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun start() {
        _isLoading.value = true
        scope.launch {
            try {
                AuthorizationApiClient.createTransaction(
                    AppConstants.requestedCredentialMetadataConfig
                )
                    ?.let {
                        _qrBase64.value = it.qrCodePng
                        _qrUrl.value = it.qrCodeUrl
                        AuthorizationApiClient.pollForResult(it.id).collect { authenticatedUser ->
                            UserApiClient.getUserById(authenticatedUser.id).let { response ->
                                if (response != null) {
                                    _dbUser.value = response
                                } else {
                                    _authenticatedUserCredential.value = authenticatedUser
                                }
                                _isSuccess.value = true
                                _isLoading.value = false
                            }
                        }
                    }
            } catch (e: Throwable) {
                println("Error from start-transaction: ${e.message}")
                // expose error state if needed
                _isLoading.value = false
            }
        }
    }

    fun authenticateUser(username: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val responseUser = UserApiClient.authenticateUser(username, password)
            _dbUser.value = responseUser
            _isSuccess.value = responseUser != null
        }
    }

    fun reset() {
        _dbUser.value = null
        _qrUrl.value = null
        _qrBase64.value = null
        _isLoading.value = false
        _isSuccess.value = false
    }

    fun cancel() {
        scope.cancel()
        reset()
    }
}
