package io.melbybaldove.authentication.api

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
data class ValidateRequestTokenRequest(
    val username: String,
    val password: String,
    val request_token: String
)