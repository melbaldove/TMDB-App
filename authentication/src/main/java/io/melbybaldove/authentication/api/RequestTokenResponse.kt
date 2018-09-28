package io.melbybaldove.authentication.api

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
data class RequestTokenResponse(
    val success: Boolean,
    val expires_at: String,
    val request_token: String
)