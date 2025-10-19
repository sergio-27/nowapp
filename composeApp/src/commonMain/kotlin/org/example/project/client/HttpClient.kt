package org.example.project.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json

val apiClient = HttpClient {
    install(ContentNegotiation) {
        json()
    }
    HttpResponseValidator {
        validateResponse { response ->
            if (!response.status.isSuccess()) {
                // Read body as string for caching (suspend call)
                val responseText = response.body<String>()
                throw ResponseException(response, responseText)
            }
        }
    }
}
