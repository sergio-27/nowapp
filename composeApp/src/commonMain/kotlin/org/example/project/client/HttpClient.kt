package org.example.project.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

val apiClient = HttpClient {
    install(ContentNegotiation) {
        json()
    }
}
