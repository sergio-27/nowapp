package org.example.project.routes

import kotlinx.serialization.Serializable

@Serializable
open class Route

@Serializable
data class ActivateCredentialScreenRoute(val authenticatedUserCredential: String): Route()

@Serializable
object LoginScreenRoute: Route()

@Serializable
object HomeScreenRoute: Route()