package io.melbybaldove.authentication

import io.melbybaldove.authentication.api.AuthenticationApi
import io.melbybaldove.authentication.api.CreateSessionRequest
import io.melbybaldove.authentication.api.ValidateRequestTokenRequest
import io.melbybaldove.data.auth.TokenRepository
import javax.inject.Inject

class AuthenticatorImpl @Inject constructor(private val authenticationApi: AuthenticationApi,
                                            private val tokenRepository: TokenRepository) : Authenticator {
    override fun authenticateWith(username: String, password: String) = authenticationApi.createRequestToken()
            .flatMap { authenticationApi.validateRequestToken(ValidateRequestTokenRequest(username, password, it.request_token)) }
            .flatMap { authenticationApi.createSession(CreateSessionRequest(it.request_token)) }
            .map { it.session_id }
            .flatMap(tokenRepository::saveToken)
            .map { it.token }!!
}