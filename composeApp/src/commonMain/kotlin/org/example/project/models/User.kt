package org.example.project.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val surname: String,
    val birthDate: String,
    val email: String,
    val createdAt: String = "",
    val password: String
)
