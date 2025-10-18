package org.example.project.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticatedUserResponse(val authenticatedUser: AuthenticatedUser)