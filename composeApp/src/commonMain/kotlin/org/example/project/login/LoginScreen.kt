package org.example.project.login

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.components.dialogs.ShowQrDialogView
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun LoginScreen(
    transactionViewModel: TransactionViewModel = viewModel { TransactionViewModel() },
    onLoginSuccess: () -> Unit = {},
    onClickActivateCredentials: () -> Unit = {},
) {

    val qrUrl by transactionViewModel.qrUrl.collectAsState()
    val isSuccess by transactionViewModel.isSuccess.collectAsState()
    val authenticatedUser by transactionViewModel.authenticatedUser.collectAsState()
    val isLoading by transactionViewModel.isLoading.collectAsState()

    var responseText by remember { mutableStateOf("") }

    var isDialogOpen by remember { mutableStateOf(false) }


    LaunchedEffect(isSuccess) {
        if (isSuccess) {
            isDialogOpen = false
            if (authenticatedUser != null) {
                onLoginSuccess()
                transactionViewModel.cancel()
            }
        }
    }

    Scaffold {
        if (isSuccess && authenticatedUser == null) {
            ErrorView(
                onClickBackToLogin = {
                    transactionViewModel.reset()
                }
            )
        } else {
            LoginOptionsView(
                isLoading = isLoading,
                onClickUseQrCode = {
                    isDialogOpen = true
                    transactionViewModel.start()
                },
                onClickActivateCredential = onClickActivateCredentials
            )
        }

    }

    if (isDialogOpen) {
        ShowQrDialogView(
            isDialogOpen = isDialogOpen,
            qrUrl = qrUrl,
            responseText = responseText,
            onDismissRequest = {
                transactionViewModel.reset()
                isDialogOpen = false
            }
        )
    }

}