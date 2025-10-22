package org.example.project.login.register

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.components.buttons.PrimaryButton
import org.example.project.models.User
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun RegisterUserScreen(
    registerUserViewModel: RegisterUserViewModel = viewModel { RegisterUserViewModel() },
    authenticatedUserCredentialId: String,
    onClickRegister: () -> Unit = {},
    onClickBack: () -> Unit = {}
) {
    val isSuccess by registerUserViewModel.isSuccess.collectAsState()

    var nameTextField by remember { mutableStateOf("Pepito") }
    var surnameTextField by remember { mutableStateOf("Pepitez") }
    var birthDateTextField by remember { mutableStateOf("1998-01-01") }
    var emailTextField by remember { mutableStateOf("pepitron@gmail.com") }
    var passwordTextField by remember { mutableStateOf("Pepepe") }
    var repeatPasswordTextField by remember { mutableStateOf("Pepepe") }


    val onNameTextFieldChange: (String) -> Unit = { nameTextField = it }
    val onSurnameTextFieldChange: (String) -> Unit = { surnameTextField = it }
    val onEmailTextFieldChange: (String) -> Unit = { emailTextField = it }
    val onBirthDateTextFieldChange: (String) -> Unit = { birthDateTextField = it }
    val onPasswordTextFieldChange: (String) -> Unit = { passwordTextField = it }
    val onRepeatPasswordTextFieldChange: (String) -> Unit = { repeatPasswordTextField = it }

    LaunchedEffect(isSuccess) {
        if (isSuccess) {
            onClickRegister()
        }
    }
    Scaffold {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("VAMO A REGISTRARNOS MINISTROOOO")
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                TextField(
                    value = nameTextField,
                    placeholder = { Text("Name") },
                    onValueChange = onNameTextFieldChange,
                )
                TextField(
                    value = surnameTextField,
                    placeholder = { Text("Surname") },
                    onValueChange = onSurnameTextFieldChange,
                )
                TextField(
                    value = emailTextField,
                    placeholder = { Text("Email") },
                    onValueChange = onEmailTextFieldChange,
                )
                TextField(
                    value = birthDateTextField,
                    placeholder = { Text("Birth date") },
                    onValueChange = onBirthDateTextFieldChange,
                )
                TextField(
                    value = passwordTextField,
                    placeholder = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = onPasswordTextFieldChange,
                )
                TextField(
                    value = repeatPasswordTextField,
                    visualTransformation = PasswordVisualTransformation(),
                    placeholder = { Text("Repeat Password") },
                    onValueChange = onRepeatPasswordTextFieldChange,
                )
            }
            Column {
                PrimaryButton(
                    buttonText = "Registrarse",
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                    onClick = {
                        //TODO use form validator
                        if (passwordTextField == repeatPasswordTextField) {
                            registerUserViewModel.createUser(
                                User(
                                    name = nameTextField,
                                    surname = surnameTextField,
                                    birthDate = birthDateTextField,
                                    email = emailTextField,
                                    password = passwordTextField,
                                    id = authenticatedUserCredentialId,
                                )
                            )
                        }

                    }
                )
                PrimaryButton(
                    buttonText = "Volvel pal Login",
                    onClick = onClickBack
                )
            }
        }
    }
}