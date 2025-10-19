package org.example.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import at.asitplus.wallet.lib.Initializer
import org.example.project.home.HomeScreen
import org.example.project.login.LoginScreen
import org.example.project.login.activate.ActivateCredentialScreen
import org.example.project.routes.HomeScreenRoute
import org.example.project.routes.LoginScreenRoute
import org.example.project.routes.ActivateCredentialScreenRoute
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {

    Initializer.initOpenIdModule()
    val navController = rememberNavController()

    MaterialTheme {
        NavHost(navController = navController, startDestination = LoginScreenRoute) {
            composable<LoginScreenRoute> {
                LoginScreen(
                    onClickActivateCredentials = {
                        navController.navigate(ActivateCredentialScreenRoute)
                    },
                    onLoginSuccess = {
                        navController.navigate(HomeScreenRoute)
                    })
            }
            composable<HomeScreenRoute> { HomeScreen() }
            // You can add more destinations similarly

            composable<ActivateCredentialScreenRoute> {
                ActivateCredentialScreen(
                    onClickBack = { navController.navigateUp() }
                )
            }
        }
    }
}

