package org.example.project.login

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.cancel
import org.example.project.client.ApiClient
import org.example.project.client.Config

class TransactionViewModel(
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main + Job())
) {
    private val _qrBase64 = MutableStateFlow<String?>(null)
    val qrBase64: StateFlow<String?> = _qrBase64

    private val _qrUrl = MutableStateFlow<String?>(null)
    val qrUrl: StateFlow<String?> = _qrUrl

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun start(body: Config) {
        _isLoading.value = true
        scope.launch {
            try {
                ApiClient.createTransaction(body)?.let {
                    _qrBase64.value = it.qrCodePng
                    _qrUrl.value = it.qrCodeUrl
                    ApiClient.pollForResult(it.id).collect { success ->
                        if (success) {
                            _isSuccess.value = true
                            _isLoading.value = false
                        }
                    }
                }
            } catch (e: Throwable) {
                // expose error state if needed
                _isLoading.value = false
            }
        }
    }

    fun cancel() {
        scope.cancel()
        _qrUrl.value = null
        _qrBase64.value = null
        _isLoading.value = false
        _isSuccess.value = false
    }
}
