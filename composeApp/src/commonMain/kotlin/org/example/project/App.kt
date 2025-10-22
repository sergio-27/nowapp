package org.example.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import at.asitplus.wallet.lib.Initializer
import org.example.project.home.HomeScreen
import org.example.project.login.LoginScreen
import org.example.project.login.register.RegisterUserScreen
import org.example.project.routes.ActivateCredentialScreenRoute
import org.example.project.routes.HomeScreenRoute
import org.example.project.routes.LoginScreenRoute
import org.example.project.routes.Route
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {

    Initializer.initOpenIdModule()
    val navController = rememberNavController()

    val navigateAndClean: (Route) -> Unit = { route ->
        navController.navigate(route) {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
        }
    }
    val navigate: (Route) -> Unit = { route ->
        navController.navigate(route)
    }

    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }

    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = LoginScreenRoute
            //startDestination = ActivateCredentialScreenRoute("")
        ) {
            composable<LoginScreenRoute> {
                LoginScreen(
                    onClickLogin = {
                        //TODO authenticate user credentials here
                        navigateAndClean(HomeScreenRoute)
                    },
                    onClickRegisterUser = { authenticatedUserCredential ->
                        navigateAndClean(ActivateCredentialScreenRoute(authenticatedUserCredential.id))
                    },
                    onLoginSuccess = {
                        navigateAndClean(HomeScreenRoute)
                    })
            }
            composable<HomeScreenRoute> { HomeScreen() }
            // You can add more destinations similarly

            composable<ActivateCredentialScreenRoute> { backStackEntry ->
                val route = backStackEntry.toRoute<ActivateCredentialScreenRoute>()
                RegisterUserScreen(
                    authenticatedUserCredentialId = route.authenticatedUserCredential,
                    onClickBack = {
                        navigateAndClean(LoginScreenRoute)
                    }
                )
            }
        }
    }
}

