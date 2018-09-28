package io.melbybaldove.authentication.api

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */

interface AuthenticationApi {
    @GET("authentication/token/new")
    fun createRequestToken(): Single<RequestTokenResponse>
    @POST("authentication/token/validate_with_login")
    fun validateRequestToken(@Body request: ValidateRequestTokenRequest): Single<RequestTokenResponse>
    @POST("authentication/session/new")
    fun createSession(@Body request: CreateSessionRequest): Single<SessionResponse>
}