@file:OptIn(ExperimentalSerializationApi::class)

package org.example.project.models

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable


@Serializable
data class RegisterUserModel(
    val id: String = "",
    val name: String = "",
    val surname: String = "",
    val birthDate: String = "",
    val email: String = "",
    val password: String = ""
)
