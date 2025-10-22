package org.example.project.models

import kotlinx.serialization.Serializable


@Serializable
data class LoginUser(
    val email: String = "",
    val password: String = ""
)
