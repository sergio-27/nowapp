package org.example.project.client

import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.ExperimentalSerializationApi
import org.example.project.models.LoginUser
import org.example.project.models.RegisterUserModel
import org.example.project.models.User
import org.example.project.utils.AppConstants

object UserApiClient {

    /**
     * Get /users/{id} every pollIntervalMillis until the server responds 200 OK.
     * Emits true when success; completes after that.
     */
    suspend fun getUserById(id: String): User? {
        try {
            //TODO mcreate mapper for responses from DTO to Model
            print("https://interzonal-flurriedly-madisyn.ngrok-free.dev/users/$id")
            return apiClient.get("${AppConstants.requestedCredentialMetadataConfig.urlPrefix}/users/$id")
                .body<User?>()
        } catch (error: ResponseException) {
            print("Error on ping: ${error}")
            return null
        }

    }

    /**
     * Get /users/{id} every pollIntervalMillis until the server responds 200 OK.
     * Emits true when success; completes after that.
     */
    suspend fun authenticateUser(email: String, password: String): User? {
        try {
            //TODO create mapper for responses from DTO to Model
            print("https://interzonal-flurriedly-madisyn.ngrok-free.dev/users/auth")
            return apiClient.post("${AppConstants.requestedCredentialMetadataConfig.urlPrefix}/users/auth") {
                contentType(ContentType.Application.Json)
                setBody(LoginUser(email = email, password = password))
            }.body()
        } catch (error: ResponseException) {
            print("Error on /users/auth: $error")
            return null
        }

    }

    /**
     * Polls /api/single/{id} every pollIntervalMillis until the server responds 200 OK.
     * Emits true when success; completes after that.
     */
    //TODO map response to User model
    @OptIn(ExperimentalSerializationApi::class)
    suspend fun createUser(body: RegisterUserModel): String? {
        try {
            val response = apiClient.post("${AppConstants.requestedCredentialMetadataConfig.urlPrefix}/users") {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            return if (response.status == HttpStatusCode.OK) {
                body.id
            } else {
                null
            }
        } catch (error: Exception) {
            print("Error on ping: $error")
            return null
        }

    }
}
