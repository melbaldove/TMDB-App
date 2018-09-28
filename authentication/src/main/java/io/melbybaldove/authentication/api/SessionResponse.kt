package io.melbybaldove.authentication.api

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
data class SessionResponse(
    val success: Boolean,
    val session_id: String
)