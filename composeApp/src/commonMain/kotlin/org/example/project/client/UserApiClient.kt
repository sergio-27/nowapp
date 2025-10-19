package org.example.project.client

import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.get
import org.example.project.models.User

object UserApiClient {

    suspend fun getUserById(id: String): User? {
        try {
            print("https://interzonal-flurriedly-madisyn.ngrok-free.dev/users/$id")
            return apiClient.get("https://interzonal-flurriedly-madisyn.ngrok-free.dev/users/$id").body<User>()
        } catch (error: ResponseException) {
            print("Error on ping: ${error}")
            return null
        }

    }
}
