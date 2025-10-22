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
import org.example.project.models.AuthenticatedUser
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun LoginScreen(
    transactionViewModel: TransactionViewModel = viewModel { TransactionViewModel() },
    onLoginSuccess: () -> Unit = {},
    onClickLogin: () -> Unit = {},
    onClickRegisterUser: (authenticatedUserCredential: AuthenticatedUser) -> Unit = {},
) {

    val qrUrl by transactionViewModel.qrUrl.collectAsState()
    val isSuccess by transactionViewModel.isSuccess.collectAsState()
    val authenticatedUserCredential by transactionViewModel.authenticatedUserCredential.collectAsState()
    val dbUser by transactionViewModel.dbUser.collectAsState()
    val isLoading by transactionViewModel.isLoading.collectAsState()

    var responseText by remember { mutableStateOf("") }

    var isDialogOpen by remember { mutableStateOf(false) }


    LaunchedEffect(isSuccess) {
        if (isSuccess) {
            isDialogOpen = false
            if (dbUser != null) {
                onLoginSuccess()
                transactionViewModel.cancel()
            }
        }
    }

    Scaffold {
        if (isSuccess && dbUser == null) {
            ErrorView(
                authenticatedUserCredential = authenticatedUserCredential,
                onClickRegisterUser = onClickRegisterUser,
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
                onClickLogin = onClickLogin
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