package org.example.project.login

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.components.dialogs.ShowQrDialogView
import org.example.project.models.Config
import org.example.project.models.Credential
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun LoginScreen(
    transactionViewModel: TransactionViewModel = viewModel { TransactionViewModel() },
    onLoginSuccess: () -> Unit,
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
            }
        }
    }

    Scaffold {
        if (isSuccess && authenticatedUser == null) {
            // Show authenticated user details or navigate to another screen
            Text("Error: Unable to retrieve user details.")
        } else {
            LoginOptionsView(
                isLoading = isLoading,
                onClickUseQrCode = {
                    isDialogOpen = true
                    transactionViewModel.start(
                        Config(
                            urlPrefix = "https://interzonal-flurriedly-madisyn.ngrok-free.dev",
                            credentials = listOf(
                                Credential(
                                    credentialType = "urn:eu.europa.ec.eudi:pid:1",
                                    representation = "SD_JWT",
                                    attributes = listOf(
                                        "family_name",
                                        "given_name",
                                        "birth_date"
                                    )
                                )
                            )
                        )
                    )
                },
                onClickActivateCredential = {}
            )
        }

    }

    if (isDialogOpen) {
        ShowQrDialogView(
            isDialogOpen = isDialogOpen,
            qrUrl = qrUrl,
            responseText = responseText,
            onDismissRequest = {
                transactionViewModel.cancel()
                isDialogOpen = false
            }
        )
    }

}